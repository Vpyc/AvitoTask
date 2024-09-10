package com.example.avitotask.repository

import com.example.avitotask.retrofit.Product
import com.example.avitotask.retrofit.RetrofitClient
import com.google.gson.Gson

class ProductRepository (private val gson: Gson){

    suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = RetrofitClient.productsApi.getProducts()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Ошибка при получении продуктов: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}