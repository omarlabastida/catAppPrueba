package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatBreedsRepository

class UpdateCatBreedUseCase(private val catBreedsRepository: CatBreedsRepository) {

    suspend fun execute(idCat: String, isFavorite: Boolean) {
        catBreedsRepository.updateFavoriteCat(idCat, isFavorite)
    }
}