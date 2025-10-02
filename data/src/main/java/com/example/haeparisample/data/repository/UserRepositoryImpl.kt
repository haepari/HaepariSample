package com.example.haeparisample.data.repository

import com.example.haeparisample.data.local.datastore.PreferencesManager
import com.example.haeparisample.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : UserRepository {

    override fun isLoggedIn(): Flow<Boolean> = preferencesManager.isLoggedIn

    override fun skipLogin(): Flow<Boolean> = preferencesManager.skipLogin

    override fun userToken(): Flow<String?> = preferencesManager.userToken

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        preferencesManager.setLoggedIn(isLoggedIn)
    }

    override suspend fun setSkipLogin(skip: Boolean) {
        preferencesManager.setSkipLogin(skip)
    }

    override suspend fun setUserToken(token: String) {
        preferencesManager.setUserToken(token)
    }

    override suspend fun setUserId(userId: String) {
        preferencesManager.setUserId(userId)
    }

    override suspend fun clearUserData() {
        preferencesManager.clearUserData()
    }
}
