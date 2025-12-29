package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.repository.GetPanchayatListRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPanchayatListUseCase @Inject constructor(
    private val repository: GetPanchayatListRepository
){
     operator fun invoke(): Flow<List<tblClusterList>> {
       val data = repository.getPanchayatList()
        return data
    }

    fun getFirstPanchayat(): Flow<tblClusterList?> {
        return repository.getPanchayatList()
            .map { list -> list.firstOrNull() }
    }
   fun getPanchayatById(panchayatId: String): Flow<tblClusterList?> {
        return repository.getPanchayatById(panchayatId)
    }
}
