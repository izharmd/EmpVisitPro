package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.domain.repository.GetUserDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: GetUserDetailsRepository
) {
   /* suspend operator fun invoke(): Flow<tblLogin>{
        return repository.getUserDetails()
    }*/

    suspend fun getUserDetails()=  repository.getUserDetails()

}