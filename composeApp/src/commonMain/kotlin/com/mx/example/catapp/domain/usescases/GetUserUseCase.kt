package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.UserRepository
import com.mx.example.catapp.domain.datamodel.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    fun excecute(userName: String): Flow<UserData?> = flow {
        val dataUser = userRepository.getUserByUserName(userName)
        emit(dataUser)
    }
}