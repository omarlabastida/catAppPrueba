package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.CatBreedsRepository
import com.mx.example.catapp.data.local.repository.CatFavoritesRepository
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.datamodel.CatImage
import com.mx.example.catapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SyncCatBreedsUseCase(
    private val catBreedsRepository: CatBreedsRepository,
    private val catFavoriteRepository: CatFavoritesRepository,
    private val catRepository: CatRepository
) {
    fun excecute(idUser: String, isUpdate: Boolean): Flow<List<CatBreadResponse>> = flow {
        try {
            val response = catRepository.getCatBreeds()

            catBreedsRepository.deleteAllCatsBreeds()

            var listaFavoritos = catFavoriteRepository.getAllCatsFavorites(idUser)
            println("LABASTIDA - listaFavoritos: $listaFavoritos")
            if(listaFavoritos == null) listaFavoritos = emptyList()
            val favoriteIdCats = listaFavoritos
                .mapNotNull { it.image?.id }
                .toSet()
            println("LABASTIDA - FAVORITOSids: $favoriteIdCats")
            val updatedList = response.map { cat ->
                cat.copy(
                    isFavorite = favoriteIdCats.contains(cat.image?.id) // true si está en favoritos, false si no
                )
            }
            println("LABASTIDA - UPDATED LIST: $updatedList")

            updatedList.forEach { cat ->
                catBreedsRepository.insertCatBreed(cat)
            }

            emit(updatedList)
        } catch (e: Exception) {
            println("LABASTIDA - ERROR: ${e.message}")

            val localDataList = catBreedsRepository.getAllCatsBreeds().first()
            val favoriteIdCats = catFavoriteRepository.getAllCatsFavorites(idUser)
                .mapNotNull { it.image?.id }
                .toSet()

            val updatedCachedList = localDataList.map { cat ->
                cat.copy(
                    isFavorite = favoriteIdCats.contains(cat.image?.id),
                    image = CatImage(id = cat.image?.id, url = "")
                )
            }
            emit(updatedCachedList)
        }
    }
}
