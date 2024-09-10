package com.example.avitotask.repository

import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.retrofit.RetrofitClient
import com.google.gson.Gson

class ProductRepository (private val gson: Gson){

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

}