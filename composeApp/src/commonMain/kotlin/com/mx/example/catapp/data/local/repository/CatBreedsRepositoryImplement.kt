package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.data.local.database.CatAppDatabase
import com.mx.example.catapp.data.local.database.Cat_breed
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.datamodel.CatImage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatBreedsRepositoryImplement(private val db: CatAppDatabase): CatBreedsRepository {
    override suspend fun insertCatBreed(catData: CatBreadResponse) {
        db.catsBreedsEntityQueries.insertOrReplace(
            id_cat = catData.image?.id?:"",
            name = catData.name,
            description = catData.description,
            origin = catData.origin,
            temperament = catData.temperament,
            life_span = catData.lifeSpan,
            image = catData.image?.url?:"",
            is_favorite = if(catData.isFavorite) 1 else 0
        )
    }

    override fun getAllCatsBreeds(): Flow<List<CatBreadResponse>> {
        return db.catsBreedsEntityQueries.selectAll()
            .asFlow()
            .mapToList()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun updateFavoriteCat(idCat: String, isFavorite: Boolean) {
        db.catsBreedsEntityQueries.updateIsFavoriteById(if(isFavorite) 1 else 0, idCat)
    }

    override suspend fun deleteAllCatsBreeds() {
        db.catsBreedsEntityQueries.deleteAll()
    }

    private fun Cat_breed.toDomain() =
        CatBreadResponse(
            idB = id,
            idUser = "",
            name = name,
            description = description,
            origin = origin,
            temperament = temperament,
            lifeSpan = life_span,
            image = CatImage(id = id_cat, url = image, width = null, height = null),
            isFavorite = (is_favorite ?: 0) > 0
        )
}