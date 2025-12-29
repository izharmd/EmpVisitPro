package com.jslps.empvisist.data.remote.repository

import android.util.Log
import com.jslps.compose.utils.AppConstant
import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.api.LoginRequest
import com.jslps.empvisist.api.NetworkHelper
import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.remote.response.ResponseData
import com.jslps.empvisist.domain.repository.LoginRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val dao: AppDao
) : LoginRepository {
    
    override suspend fun login(username:String,password: String): ApiResponse<ResponseData> {
        // Prepare parameters for form-url-encoded
        return withContext(Dispatchers.IO) {
            val params = mapOf(
                "username" to "chatracrp",
                "password" to "123",
                "appVersion" to "2.9"
            )
             networkHelper.postFormUrlEncoded<ResponseData>(
                apiMethod = AppConstant.loginAPI,
                params = params
            )
        }

    }

}