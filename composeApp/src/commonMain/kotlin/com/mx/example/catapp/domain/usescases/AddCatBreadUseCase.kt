package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatBreedsRepository
import com.mx.example.catapp.domain.datamodel.CatBreadResponse

class AddCatBreadUseCase (private val catBreedsRepository: CatBreedsRepository){
    suspend fun excecute(catData: CatBreadResponse){
        catBreedsRepository.insertCatBreed(catData)
    }
}