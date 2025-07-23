package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.domain.datamodel.CatBreadResponse

interface CatFavoritesRepository{
    suspend fun insertCatFavorite(catData: CatBreadResponse)
    suspend fun getAllCatsFavorites(idUser: String): List<CatBreadResponse>
    suspend fun deleteById(idCat: String, idUser: String)
}