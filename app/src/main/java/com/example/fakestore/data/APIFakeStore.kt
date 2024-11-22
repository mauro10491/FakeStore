package com.example.fakestore.data

import com.example.fakestore.model.LoginRequest
import com.example.fakestore.model.LoginResponse
import com.example.fakestore.model.ProductsModel
import com.example.fakestore.util.Constants.Companion.ENDPOINTLOGIN
import com.example.fakestore.util.Constants.Companion.ENDPOINTPRODUCTS
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIFakeStore {

    //Login
    @POST(ENDPOINTLOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    //Products
    @GET(ENDPOINTPRODUCTS)
    suspend fun getProducts(): Response<List<ProductsModel>>

    //Products by id
    @GET("$ENDPOINTPRODUCTS/{id}")
    suspend fun getProductById(@Path(value = "id")id: Int): Response<ProductsModel>
}