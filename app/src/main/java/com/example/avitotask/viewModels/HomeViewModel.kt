package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avitotask.R
import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SortOrder {
    NONE,       // Отсутствие сортировки
    ASCENDING,  // Сортировка по возрастанию
    DESCENDING  // Сортировка по убыванию
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRep: ProductRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val _products = mutableStateOf(emptyList<ProductList>())
    val products: MutableState<List<ProductList>> = _products

    private val category = mutableStateOf<String?>(null)

    val sortOrder = mutableStateOf(SortOrder.NONE)

    private val limit = 20
    private val currentPage = mutableIntStateOf(1)
    private val fields = "name,price,discounted_price,images"

    fun setCategory(newCategory: String?) {
        category.value = newCategory
        _products.value = emptyList()
        currentPage.intValue = 1
        getProducts()
    }

    fun setSortOrder(newSortOrder: SortOrder) {
        sortOrder.value = newSortOrder
        _products.value = emptyList()
        currentPage.intValue = 1
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val sortQuery = getSortQuery(sortOrder.value)
                val response = when {
                    category.value != null && sortQuery != "" -> {
                        productRep.getProductsWithPriceSortAndCategory(
                            limit = limit,
                            page = currentPage.intValue,
                            fields = fields,
                            sort = sortQuery,
                            category = category.value!!
                        )
                    }

                    category.value != null -> {
                        productRep.getProductsWithCategory(
                            limit = limit,
                            page = currentPage.intValue,
                            fields = fields,
                            category = category.value!!
                        )
                    }

                    sortQuery != "" -> {
                        productRep.getProductsWithPriceSort(
                            limit = limit,
                            page = currentPage.intValue,
                            fields = fields,
                            sort = sortQuery
                        )
                    }

                    else -> {
                        productRep.getProductsByPage(
                            limit = limit,
                            page = currentPage.intValue,
                            fields = fields,
                        )
                    }
                }

                if (response.isSuccess) {
                    _products.value += response.getOrNull()!!
                    currentPage.intValue++
                } else {
                    _errorMessage.value =
                        response.exceptionOrNull()?.localizedMessage
                            ?: resourceProvider.getStringById(R.string.loading_error)
                }
            } catch (e: Exception) {
                _errorMessage.value =
                    resourceProvider.getStringById(R.string.error) + ": ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun getSortQuery(sortOrder: SortOrder): String {
        return when (sortOrder) {
            SortOrder.ASCENDING -> "+price"
            SortOrder.DESCENDING -> "-price"
            SortOrder.NONE -> ""
        }
    }
}