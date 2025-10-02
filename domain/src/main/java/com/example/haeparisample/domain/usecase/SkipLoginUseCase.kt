package com.example.haeparisample.domain.usecase

import com.example.haeparisample.domain.repository.UserRepository
import javax.inject.Inject

class SkipLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.setSkipLogin(true)
    }
}
