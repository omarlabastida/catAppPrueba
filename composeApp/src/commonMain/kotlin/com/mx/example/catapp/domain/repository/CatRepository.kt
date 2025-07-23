package com.mx.example.catapp.domain.repository

import com.mx.example.catapp.domain.datamodel.CatBreadResponse

interface CatRepository {
    suspend fun getCatBreeds(): List<CatBreadResponse>
}