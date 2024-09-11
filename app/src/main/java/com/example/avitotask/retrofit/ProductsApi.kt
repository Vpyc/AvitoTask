package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") productId: String
    ): Response<ProductByIdResponse>

    @GET("products")
    suspend fun getProductsByPage(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String
    ): Response<ProductsResponse>

    @GET("products")
    suspend fun getProductsWithPriceSort(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String,
        @Query("sort") sort: String
    ): Response<ProductsResponse>

    @GET("products")
    suspend fun getProductsWithPriceSortAndCategory(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String,
        @Query("sort") sort: String,
        @Query("category") category: String,
    ): Response<ProductsResponse>

    @GET("products")
    suspend fun getProductsWithCategory(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String,
        @Query("category") category: String,
    ): Response<ProductsResponse>

}