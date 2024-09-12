package com.example.avitotask.repository.impl

import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.Product
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.retrofit.RetrofitClient

class ProductRepositoryImpl : ProductRepository {

    override suspend fun getProducts(): Result<List<ProductList>> {
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

    override suspend fun getProductById(productId: String): Result<Product> {
        return try {
            val response = RetrofitClient.productsApi.getProductById(productId)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    Result.success(apiResponse.data)
                } else {
                    Result.failure(Exception("Пустой ответ от сервера"))
                }
            } else {
                Result.failure(Exception("Ошибка при получении продукта: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProductsByPage(
        limit: Int,
        page: Int,
        fields: String
    ): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProductsByPage(limit, page, fields)
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

    override suspend fun getProductsWithCategory(
        limit: Int,
        page: Int,
        fields: String,
        category: String
    ): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProductsWithCategory(
                limit, page, fields, category
            )
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

    override suspend fun getProductsWithPriceSort(
        limit: Int,
        page: Int,
        fields: String,
        sort: String
    ): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProductsWithPriceSort(
                limit, page, fields, sort
            )
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

    override suspend fun getProductsWithPriceSortAndCategory(
        limit: Int,
        page: Int,
        fields: String,
        sort: String,
        category: String
    ): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProductsWithPriceSortAndCategory(
                limit, page, fields, sort, category
            )
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