package com.mx.example.catapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.domain.datamodel.UserData
import com.mx.example.catapp.domain.usescases.TotalUsesCase
import com.mx.example.catapp.utils.MesagesToast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatViewModel(private val totalUsesCase: TotalUsesCase, private val mesagesToast: MesagesToast) : ViewModel() {

    private val _breeds = MutableStateFlow<List<CatBreadResponse>>(emptyList())
    val breeds: StateFlow<List<CatBreadResponse>> = _breeds

    private val _favoriteCats = MutableStateFlow<List<CatBreadResponse>>(emptyList())
    val favoriteCats: StateFlow<List<CatBreadResponse>> = _favoriteCats

    private val _user = MutableStateFlow<UserData?>(null)
    val user: StateFlow<UserData?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun resetUser() {
        _user.value = null
    }

    fun resetBreeds() {
        _breeds.value = emptyList()
    }

    fun showMessages(message: String) {
        mesagesToast.showToast(message)
    }

    var actualUser = UserData()

    var catSelect = CatBreadResponse()

    fun getCatsList(isUpdate: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                totalUsesCase.syncCatBreedsUseCase.excecute(actualUser.id.toString(), isUpdate)
                    .collect {
                        _isLoading.value = false
                        _breeds.value = it
                        println("LABASTIDA $it")
                    }
            } catch (e: Exception) {
                println("ERROR")

            } finally {
                _isLoading.value = false
            }
        }
    }



    //Obtener usuario por nombre
    fun getUserByName(userName: String) {
        viewModelScope.launch {
            try {
                println("OBTENIENDO A BUSCAR: username $userName")
                totalUsesCase.getUserUseCase.excecute(userName)
                    .collect{
                        println("USUARIO OBTENIDO: ${it?.userName}")
                        if(it != null) {
                            _user.value = it
                        } else {
                            _user.value = UserData()
                        }
                    }

            } catch (e: Exception) {
                println("ERROR AL OBTENER USUARIO: ${e.message}")
                _user.value = null
            }
        }
    }

    //Insertar usuario
    fun insertUser(user: UserData) {
        viewModelScope.launch {
            try {
                totalUsesCase.addUserUseCase.execute(user)
                println("USUARIO INSERTADO: ${user.userName}")
            } catch (e: Exception) {
                println("ERROR AL INSERTAR USUARIO: ${e.message}")
            }
        }
    }

    //Actualizar datos del gato
    fun updateCatData(catData: CatBreadResponse){
        viewModelScope.launch {
            try {
                totalUsesCase.updateCatBreed.execute(catData.image?.id?:"", catData.isFavorite)
            } catch (e: Exception) {
                println("ERROR AL OBTENER LAS RAZAS DE GATOS: ${e.message}")
            }
        }
    }


    fun addCatFavorite(dataCat: CatBreadResponse) {
        viewModelScope.launch {
            try {
                totalUsesCase.addCatFavorite.execute(dataCat)
            } catch (e: Exception) {
                println("ERROR AL AÑADIR GATO A FAVORITOS: ${e.message}")
            }
        }
    }

    fun deleteCatFaorite(idCat: String, idUser: String) {
        viewModelScope.launch {
            try {
                totalUsesCase.deleteFavoriteUsesCase.execute(idCat, idUser)
            } catch (e: Exception) {
                println("ERROR AL ELIMINAR GATO DE FAVORITOS: ${e.message}")
            }
        }
    }

    fun getAllCatsFavorites(idUser: String) {
        viewModelScope.launch {
            try {
                totalUsesCase.getCatsFavoritesUsesCase.execute(idUser)
                    .collect { list ->
                        _favoriteCats.value = list
                    }
            } catch (e: Exception) {
                println("ERROR AL OBTENER GATOS FAVORITOS: ${e.message}")
            }
        }
    }
}