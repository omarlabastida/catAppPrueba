package com.mx.example.catapp.di

import com.mx.example.catapp.data.local.repository.CatBreedsRepository
import com.mx.example.catapp.data.local.repository.CatBreedsRepositoryImplement
import com.mx.example.catapp.data.local.repository.CatFaoritesRepositoryImplement
import com.mx.example.catapp.data.local.repository.CatFavoritesRepository
import com.mx.example.catapp.data.local.repository.UserRepository
import com.mx.example.catapp.data.local.repository.UserRepositoryImplement
import com.mx.example.catapp.data.repository.CatRepositoryImplement
import com.mx.example.catapp.domain.repository.CatRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<CatRepository> {CatRepositoryImplement(get()) }
    single<UserRepository> {UserRepositoryImplement(get())}
    single<CatBreedsRepository> {CatBreedsRepositoryImplement(get())}
    single<CatFavoritesRepository> {CatFaoritesRepositoryImplement(get())}
}