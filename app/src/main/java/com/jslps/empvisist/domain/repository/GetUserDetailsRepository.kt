package com.jslps.empvisist.domain.repository

import com.jslps.empvisist.data.local.entites.tblLogin
import kotlinx.coroutines.flow.Flow

interface GetUserDetailsRepository{
    suspend fun getUserDetails(): Flow<tblLogin>
}


