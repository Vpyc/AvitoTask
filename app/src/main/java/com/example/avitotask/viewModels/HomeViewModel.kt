package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.ProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRep: ProductRepository
) : BaseViewModel() {

    private val _products = mutableStateOf(emptyList<ProductList>())
    val products: MutableState<List<ProductList>> = _products

    private val limit = 20
    private val currentPage = mutableIntStateOf(1)
    private val fields = "name,price,discounted_price,images"

    fun getProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = productRep.getProducts()
                if (response.isSuccess) {
                    _products.value = response.getOrNull()!!
                    _isLoading.value = false
                } else {
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun getProductsByPage() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = productRep.getProductsByPage(limit, currentPage.intValue, fields)
                if (response.isSuccess) {
                    _products.value += response.getOrNull()!!
                    _isLoading.value = false
                    currentPage.intValue++
                } else {
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}