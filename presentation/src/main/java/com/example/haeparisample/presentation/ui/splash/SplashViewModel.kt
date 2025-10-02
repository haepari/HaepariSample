package com.example.haeparisample.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haeparisample.domain.usecase.AuthStatus
import com.example.haeparisample.domain.usecase.GetAuthStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAuthStatusUseCase: GetAuthStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            try {
                delay(2000) // Splash screen delay

                getAuthStatusUseCase()
                    .firstOrNull()
                    ?.let { authStatus ->
                        _uiState.value = when (authStatus) {
                            is AuthStatus.Authenticated -> SplashUiState.NavigateToHome
                            is AuthStatus.Unauthenticated -> SplashUiState.NavigateToLogin
                        }
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error checking auth status")
                _uiState.value = SplashUiState.NavigateToLogin
            }
        }
    }
}

sealed class SplashUiState {
    data object Loading : SplashUiState()
    data object NavigateToLogin : SplashUiState()
    data object NavigateToHome : SplashUiState()
}
