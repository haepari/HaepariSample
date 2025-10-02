package com.example.haeparisample.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun isLoggedIn(): Flow<Boolean>
    fun skipLogin(): Flow<Boolean>
    fun userToken(): Flow<String?>
    suspend fun setLoggedIn(isLoggedIn: Boolean)
    suspend fun setSkipLogin(skip: Boolean)
    suspend fun setUserToken(token: String)
    suspend fun setUserId(userId: String)
    suspend fun clearUserData()
}
