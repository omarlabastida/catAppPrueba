package com.mx.example.catapp.domain.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class CatImage(
    val id: String? = null,
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)
