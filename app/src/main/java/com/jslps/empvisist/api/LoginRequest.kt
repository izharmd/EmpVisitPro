package com.jslps.empvisist.api

// Request model
data class LoginRequest(
    val UserName: String = "chatracrp",  // Non-nullable
    val Password: String = "123",  // Non-nullable
    val AppVersion: String = "2.8"  // Non-nullable with default
)
