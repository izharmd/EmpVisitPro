package com.jslps.empvisist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.data.remote.response.ResponseData
import com.jslps.empvisist.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // UI State
    private val _loginState = MutableStateFlow<ApiResponse<ResponseData?>>(ApiResponse.Idle)
    val loginState: StateFlow<ApiResponse<ResponseData?>> = _loginState.asStateFlow()

    fun onLoginClick(username:String,password: String) {
        performLogin(username, password)
    }

    private fun performLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = ApiResponse.Loading("Logging in...")
            try {
                when (val result = loginUseCase(username, password)) {
                    is ApiResponse.Success -> {
                        _loginState.value = result
                        _password.value = ""
                    }

                    is ApiResponse.Error -> {
                        _loginState.value = result
                    }

                    is ApiResponse.Loading -> {
                        _loginState.value = ApiResponse.Error("Unexpected loading")
                    }

                    ApiResponse.Idle -> {
                        _loginState.value = ApiResponse.Error("Request idle")
                    }
                }

            } catch (e: Exception) {
                _loginState.value = ApiResponse.Error("Exception: ${e.message}")
            }
        }

    }

    fun clearLoginState() {
        _loginState.value = ApiResponse.Idle
    }

    // Form fields
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()
    fun updateUsername(value: String) {
        _username.value = value
    }
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    fun updatePassword(value: String) {
        _password.value = value
    }
}