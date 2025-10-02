package com.example.haeparisample.domain.usecase

import com.example.haeparisample.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return try {
            // TODO: Implement actual login logic with API call
            // For now, just simulate a login
            kotlinx.coroutines.delay(1000)

            userRepository.setLoggedIn(true)
            userRepository.setUserToken("dummy_token")

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
