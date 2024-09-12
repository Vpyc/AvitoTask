package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRep: ProductRepository
) : BaseViewModel() {

    private val _product = mutableStateOf<Product?>(null)
    val product: MutableState<Product?> = _product

    fun getProductById(productId: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val response = productRep.getProductById(productId)
                if (response.isSuccess) {
                    _product.value = response.getOrNull()!!
                } else {
                    _errorMessage.value =
                        response.exceptionOrNull()?.localizedMessage ?: "Ошибка при загрузке данных"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}