package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductsApi {
    @POST("products")
    suspend fun getProducts(): Response<List<Product>>
}