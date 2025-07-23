package com.mx.example.catapp.data.local.repository

import com.mx.example.catapp.data.local.database.CatAppDatabase
import com.mx.example.catapp.data.local.database.UserEntity
import com.mx.example.catapp.domain.datamodel.UserData
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class UserRepositoryImplement(private val db: CatAppDatabase): UserRepository {
    override fun getAllUsers(): Flow<List<UserData>> {
        return db.userEntityQueries.selectAll()
            .asFlow()
            .mapToList()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getUserByUserName(userName: String): UserData? {
        return try{
            db.userEntityQueries.selectByUserName(userName).executeAsOneOrNull()?.toDomain()
        }catch (e: Exception) {
            println("Error fetching user by username: ${e.message}")
            null
        }

    }

    override suspend fun insertUser(user: UserData) = withContext(Dispatchers.IO) {
        db.userEntityQueries.insertUser(user.userName, user.password)
    }

    override suspend fun deleteAllUsers() {
        db.userEntityQueries.deleteAll()
    }

    private fun UserEntity.toDomain() =
        UserData(id = id, userName = userName, password = password)
}