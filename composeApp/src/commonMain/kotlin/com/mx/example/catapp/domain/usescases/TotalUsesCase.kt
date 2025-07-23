package com.mx.example.catapp.domain.usescases

class TotalUsesCase (
    val addCatBreed: AddCatBreadUseCase,
    val addUserUseCase: AddUserUseCase,
    val addCatFavorite: AddCatFavoriteUseCase,
    val deleteCatsBreeds: DeleteCatsBreedsUseCase,
    val deleteFavoriteUsesCase: DeleteFavoriteUsesCase,
    val getCatBreedsUseCase: GetCatBreedsUseCase,
    val getCatsFavoritesUsesCase: GetCatsFavoritesUsesCase,
    val getUserUseCase: GetUserUseCase,
    val updateCatBreed: UpdateCatBreedUseCase,
    val syncCatBreedsUseCase: SyncCatBreedsUseCase
)