package com.mx.example.catapp.domain.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatBreadResponse(
    val idB: Long?=0,
    val id: String? = "",
    val idUser: String? = "",
    val name: String? = "",
    val description: String? = "",
    val origin: String? = "",
    val temperament: String? = "",
    @SerialName("life_span")
    val lifeSpan: String? = "",
    val image: CatImage? = null,
    val isFavorite: Boolean = false
)
