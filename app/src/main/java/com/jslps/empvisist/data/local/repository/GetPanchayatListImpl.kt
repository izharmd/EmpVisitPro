package com.jslps.empvisist.data.local.repository

import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.repository.GetPanchayatListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPanchayatListImpl @Inject constructor(
    private val dao: AppDao
) : GetPanchayatListRepository {
    override  fun getPanchayatList(): Flow<List<tblClusterList>> {
        return dao.getPanchayatList()
    }

    override fun getPanchayatById(panchayatId: String): Flow<tblClusterList> {
        TODO("Not yet implemented")
    }

}