package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatFavoritesRepository

class DeleteFavoriteUsesCase(
    private val catFavoritesRepository: CatFavoritesRepository
) {
    suspend fun execute(catId: String, idUser: String) {
        catFavoritesRepository.deleteById(catId, idUser)
    }
}