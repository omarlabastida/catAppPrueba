package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatBreedsRepository

class DeleteCatsBreedsUseCase(private val catBreedsRepository: CatBreedsRepository) {
    suspend fun execute() {
        catBreedsRepository.deleteAllCatsBreeds()
    }
}