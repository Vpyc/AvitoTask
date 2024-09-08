package com.example.avitotask.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST("app/v1/users")
    suspend fun registerUser(@Body registationRequest: RegistrationRequest)
    : Response<Unit>
}