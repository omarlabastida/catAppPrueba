package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatFavoritesRepository
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCatsFavoritesUsesCase(private val catFavoritesRepository: CatFavoritesRepository) {

    fun execute(idUser: String): Flow<List<CatBreadResponse>> = flow {
        val dataCatFavorite = catFavoritesRepository.getAllCatsFavorites(idUser)
        emit(dataCatFavorite)
    }
}