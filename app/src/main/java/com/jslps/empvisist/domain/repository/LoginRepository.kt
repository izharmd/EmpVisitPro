package com.jslps.empvisist.domain.repository

import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.api.LoginRequest
import com.jslps.empvisist.data.remote.response.ResponseData
import kotlinx.coroutines.flow.MutableStateFlow

interface LoginRepository {
    suspend fun login(username:String,password: String): ApiResponse<ResponseData>
}