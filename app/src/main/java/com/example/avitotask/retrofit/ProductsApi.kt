package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>
}