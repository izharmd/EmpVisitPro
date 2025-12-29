package com.jslps.empvisist.data.local.repository

import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.domain.repository.GetUserDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsImpl @Inject constructor(
    private val dao: AppDao
) : GetUserDetailsRepository{
    override suspend fun getUserDetails(): Flow<tblLogin> {
        return dao.getUserData()
    }

}