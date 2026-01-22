package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.mapper.toDomainList
import com.jslps.empvisist.data.mapper.toEntityList
import com.jslps.empvisist.data.remote.response.ResponseData
import com.jslps.empvisist.domain.repository.LoginRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val dao: AppDao
) {
    suspend operator fun invoke(username:String,password: String): ApiResponse<ResponseData?> {
        return withContext(Dispatchers.IO) {
            when (val result = repository.login(username, password)) {
                is ApiResponse.Success -> {
                    result.data.let {
                        val data  = result.data.data
                        dao.savetblmstClusterList(data[0].ClusterList!!.toDomainList().toEntityList())
                        dao.savetblmstVillageList(data[0].VillageList!!.toDomainList().toEntityList())
                        dao.saveUserLoginData(data[0].userData!!.toDomainList().toEntityList())
                    }
                    result
                }
                else -> result
            }
        }
    }
}

