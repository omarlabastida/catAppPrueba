package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCatBreedsUseCase(private val catRepository: CatRepository) {

    fun invoke(): Flow<List<CatBreadResponse>> = flow {
        val breeds = catRepository.getCatBreeds() // repository puede ser suspend o Flow
        emit(breeds)
    }
}