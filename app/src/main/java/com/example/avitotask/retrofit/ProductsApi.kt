package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") productId: String
    ): Response<ProductByIdResponse>
}