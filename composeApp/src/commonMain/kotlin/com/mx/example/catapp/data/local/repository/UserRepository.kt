package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.domain.datamodel.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<UserData>>
    suspend fun getUserByUserName(userName: String): UserData?
    suspend fun insertUser(user: UserData)
    suspend fun deleteAllUsers()
}