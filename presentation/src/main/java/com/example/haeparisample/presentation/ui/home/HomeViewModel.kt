package com.example.haeparisample.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haeparisample.domain.repository.UserRepository
import com.example.haeparisample.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val isLoggedIn: Flow<Boolean> = userRepository.isLoggedIn()
    val isSkipped: Flow<Boolean> = userRepository.skipLogin()

    fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase()
                Timber.d("User logged out")
                // You might want to navigate to login screen here
                // This would typically be handled by observing the preferences in the main activity
            } catch (e: Exception) {
                Timber.e(e, "Logout failed")
            }
        }
    }
}
