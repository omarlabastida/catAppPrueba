package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatFavoritesRepository
import com.mx.example.catapp.domain.datamodel.CatBreadResponse

class AddCatFavoriteUseCase(private val catFavoritesRepository: CatFavoritesRepository) {

    suspend fun execute(catData: CatBreadResponse) {
        catFavoritesRepository.insertCatFavorite(catData)
    }
}