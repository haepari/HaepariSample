package com.example.haeparisample.domain.usecase

import com.example.haeparisample.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAuthStatusUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<AuthStatus> {
        return combine(
            userRepository.isLoggedIn(),
            userRepository.skipLogin()
        ) { isLoggedIn, skipLogin ->
            when {
                isLoggedIn || skipLogin -> AuthStatus.Authenticated
                else -> AuthStatus.Unauthenticated
            }
        }
    }
}

sealed class AuthStatus {
    data object Authenticated : AuthStatus()
    data object Unauthenticated : AuthStatus()
}
