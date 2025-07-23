package com.mx.example.catapp.di

import com.mx.example.catapp.domain.usescases.AddCatBreadUseCase
import com.mx.example.catapp.domain.usescases.AddCatFavoriteUseCase
import com.mx.example.catapp.domain.usescases.AddUserUseCase
import com.mx.example.catapp.domain.usescases.DeleteCatsBreedsUseCase
import com.mx.example.catapp.domain.usescases.DeleteFavoriteUsesCase
import com.mx.example.catapp.domain.usescases.GetCatBreedsUseCase
import com.mx.example.catapp.domain.usescases.GetCatsFavoritesUsesCase
import com.mx.example.catapp.domain.usescases.GetUserUseCase
import com.mx.example.catapp.domain.usescases.SyncCatBreedsUseCase
import com.mx.example.catapp.domain.usescases.TotalUsesCase
import com.mx.example.catapp.domain.usescases.UpdateCatBreedUseCase
import org.koin.dsl.module

val UsesCaseModule = module {
    factory { TotalUsesCase(get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    factory { AddCatBreadUseCase(get()) }
    factory { AddCatFavoriteUseCase(get()) }
    factory { AddUserUseCase(get()) }
    factory { DeleteCatsBreedsUseCase(get()) }
    factory { DeleteFavoriteUsesCase(get()) }
    factory { GetCatsFavoritesUsesCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { UpdateCatBreedUseCase(get()) }
    factory { GetCatBreedsUseCase(get()) }
    factory { SyncCatBreedsUseCase(get(), get(), get()) }



}