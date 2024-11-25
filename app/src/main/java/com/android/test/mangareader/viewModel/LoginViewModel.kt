package com.android.test.mangareader.viewModel

import android.app.Application
import com.android.test.mangareader.base.BaseViewModel
import com.android.test.mangareader.data.model.UserDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception

class LoginViewModel(application: Application) : BaseViewModel(application) {
    private val _userState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Empty)

    val userState: StateFlow<LoginUiState> = _userState

    sealed class LoginUiState {
        object Empty : LoginUiState()
        object Loading : LoginUiState()
        data class Success(val userDTO: UserDTO) : LoginUiState()
        data class Error(val exception: String?) : LoginUiState()
    }
}