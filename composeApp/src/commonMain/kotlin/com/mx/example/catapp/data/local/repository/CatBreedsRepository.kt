package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import kotlinx.coroutines.flow.Flow

interface CatBreedsRepository{
    suspend fun insertCatBreed(catData: CatBreadResponse)
    fun getAllCatsBreeds(): Flow<List<CatBreadResponse>>
    suspend fun updateFavoriteCat(idCat: String, isFavorite: Boolean)
    suspend fun deleteAllCatsBreeds()
}