package com.mx.example.catapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform