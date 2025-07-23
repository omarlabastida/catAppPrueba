package com.mx.example.catapp.domain.usescases

import com.mx.example.catapp.data.local.repository.UserRepository
import com.mx.example.catapp.domain.datamodel.UserData

class AddUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(user: UserData) {
        userRepository.insertUser(user)
    }
}