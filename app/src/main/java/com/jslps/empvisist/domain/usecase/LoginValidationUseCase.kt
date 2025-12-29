package com.jslps.empvisist.domain.usecase

import android.util.Patterns
import jakarta.inject.Inject

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

class LoginValidationUseCase @Inject constructor() {
    fun validateCredentials(email: String, password: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.Error("User name required")
            password.isBlank() -> ValidationResult.Error("Password required")
            else -> ValidationResult.Success
        }
    }
}