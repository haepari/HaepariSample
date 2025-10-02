package com.example.haeparisample.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haeparisample.domain.usecase.LoginUseCase
import com.example.haeparisample.domain.usecase.SkipLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val skipLoginUseCase: SkipLoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("이메일과 비밀번호를 입력해주세요")
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading

                loginUseCase(email, password)
                    .onSuccess {
                        Timber.d("Login successful")
                        _uiState.value = LoginUiState.Success
                    }
                    .onFailure { e ->
                        Timber.e(e, "Login failed")
                        _uiState.value = LoginUiState.Error(e.message ?: "로그인에 실패했습니다")
                    }
            } catch (e: Exception) {
                Timber.e(e, "Login failed")
                _uiState.value = LoginUiState.Error(e.message ?: "로그인에 실패했습니다")
            }
        }
    }

    fun skipLogin() {
        viewModelScope.launch {
            try {
                skipLoginUseCase()
                Timber.d("Login skipped")
                _uiState.value = LoginUiState.Skipped
            } catch (e: Exception) {
                Timber.e(e, "Skip login failed")
                _uiState.value = LoginUiState.Error(e.message ?: "오류가 발생했습니다")
            }
        }
    }
}

sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data object Success : LoginUiState()
    data object Skipped : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
