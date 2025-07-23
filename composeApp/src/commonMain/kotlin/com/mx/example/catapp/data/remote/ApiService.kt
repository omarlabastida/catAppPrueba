package com.mx.example.catapp.data.remote

import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers


class ApiService(private val client: HttpClient) {
    suspend fun getBreeds(): List<CatBreadResponse> {
        return client.get(Constants.BASE_URL+ "breeds"){
            headers{
                append(Constants.API_KEY_HEADER, Constants.API_KEY)
            }
        }.body()
    }
}