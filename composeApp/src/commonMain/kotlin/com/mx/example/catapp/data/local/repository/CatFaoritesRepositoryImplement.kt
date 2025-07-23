package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.data.local.database.CatAppDatabase
import com.mx.example.catapp.data.local.database.Cat_favorites
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.datamodel.CatImage

class CatFaoritesRepositoryImplement(private val db: CatAppDatabase): CatFavoritesRepository {
    override suspend fun insertCatFavorite(catData: CatBreadResponse) {
        db.favoritesEntityQueries.insertOrReplace(
            name = catData.name?:"",
            id_user = catData.idUser?:"",
            id_cat = catData.image?.id?:"",
            description = catData.description,
            origin = catData.origin,
            temperament = catData.temperament,
            life_span = catData.lifeSpan,
            image = catData.image?.url?:"",
            is_favorite = if(catData.isFavorite) 1 else 0
        )
    }

    override suspend fun getAllCatsFavorites(idUser: String): List<CatBreadResponse> {
        val rawList = db.favoritesEntityQueries
            .selectByIdUser(idUser)
            .executeAsList()

        println("LABASTIDA - RAW list: $rawList")

        return rawList.map { it.toDomain() }
    }

    override suspend fun deleteById(idCat: String, idUser: String) {
        db.favoritesEntityQueries.deleteById(idCat, idUser)
    }

    private fun Cat_favorites.toDomain() =
        CatBreadResponse(
            idB = id,
            idUser = id_user,
            name = name,
            description = description,
            origin = origin,
            temperament = temperament,
            lifeSpan = life_span,
            image = CatImage(id = id_cat, url = image, width = null, height = null),
            isFavorite = (is_favorite ?: 0) > 0
        )

}