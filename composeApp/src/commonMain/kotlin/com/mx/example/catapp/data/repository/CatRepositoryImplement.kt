package com.mx.example.catapp.data.repository

import com.mx.example.catapp.data.remote.ApiService
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.repository.CatRepository

class CatRepositoryImplement(private val apiService: ApiService): CatRepository {
    override suspend fun getCatBreeds(): List<CatBreadResponse> {
        val response = apiService.getBreeds()
        println("LABASTIDA - CatRepositoryImplement - getCatBreeds: $response")
        return response
    }


}