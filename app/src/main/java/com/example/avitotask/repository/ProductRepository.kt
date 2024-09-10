package com.example.avitotask.repository

import com.example.avitotask.retrofit.Product
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.retrofit.RetrofitClient

class ProductRepository{

    suspend fun getProducts(): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProducts()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Result.success(apiResponse?.Data ?: emptyList())
            } else {
                Result.failure(Exception("Ошибка при получении продуктов: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(productId: String): Result<Product> {
        return try {
            val response = RetrofitClient.productsApi.getProductById(productId)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse!= null){
                    Result.success(apiResponse.data)
                }
                else {
                    Result.failure(Exception("Пустой ответ от сервера"))
                }
            } else {
                Result.failure(Exception("Ошибка при получении продукта: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}