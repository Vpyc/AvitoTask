package com.example.avitotask.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.ProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRep: ProductRepository
): ViewModel(){

    private val _products = mutableStateOf(emptyList<ProductList>())
    val products: MutableState<List<ProductList>> = _products

    private val _isLoading = mutableStateOf(false)
    val isLoading: MutableState<Boolean> = _isLoading

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
}