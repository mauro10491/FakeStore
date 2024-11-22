package com.example.fakestore.repository

import com.example.fakestore.data.APIFakeStore
import com.example.fakestore.model.CategoriesModel
import com.example.fakestore.model.LoginRequest
import com.example.fakestore.model.ProductsModel
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(private val apiFakeStore: APIFakeStore) {

    suspend fun login(username: String, password: String): String {
        val response = apiFakeStore.login(LoginRequest(username, password))
        return response.token
    }

    suspend fun getProducts(): List<ProductsModel>? {
        val response = apiFakeStore.getProducts()
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getProductById(id: Int): ProductsModel? {
        val response = apiFakeStore.getProductById(id)
        if (response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun getCategories(): List<String>? {
        val response = apiFakeStore.getCategories()
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}