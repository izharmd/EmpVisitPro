package com.jslps.empvisist.api

sealed class ApiResponse<out T> {
    data object Idle : ApiResponse<Nothing>() // New Idle state
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val exception: Throwable? = null) : ApiResponse<Nothing>()
    data class Loading(val loading: String = "Loading...") : ApiResponse<Nothing>()
}