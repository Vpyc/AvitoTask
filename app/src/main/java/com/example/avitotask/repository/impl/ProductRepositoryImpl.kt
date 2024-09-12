package com.example.avitotask.repository.impl

import com.example.avitotask.R
import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.retrofit.Product
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.retrofit.RetrofitClient
import com.example.avitotask.utils.ResourceProvider
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ProductRepository {

    override suspend fun getProducts(): Result<List<ProductList>> {
        return try {
            val response = RetrofitClient.productsApi.getProducts()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Result.success(apiResponse?.Data ?: emptyList())
            } else {
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_products_loading_error) +
                                ": ${response.message()}"
                    )
                )
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
                    Result.failure(
                        Exception(
                            resourceProvider.getStringById(R.string.empty_response_error)
                        )
                    )
                }
            } else {
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_product_loading_error) +
                                ": ${response.message()}"
                    )
                )
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
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_products_loading_error) +
                                ": ${response.message()}"
                    )
                )
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
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_products_loading_error) +
                                ": ${response.message()}"
                    )
                )
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
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_products_loading_error) +
                                ": ${response.message()}"
                    )
                )
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
                Result.failure(
                    Exception
                        (
                        resourceProvider.getStringById(R.string.failed_products_loading_error) +
                                ": ${response.message()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}