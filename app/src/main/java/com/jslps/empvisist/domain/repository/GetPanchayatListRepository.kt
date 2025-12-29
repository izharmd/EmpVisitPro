package com.jslps.empvisist.domain.repository

import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.domain.model.ClusterList
import kotlinx.coroutines.flow.Flow

interface GetPanchayatListRepository {
     fun getPanchayatList(): Flow<List<tblClusterList>>
     fun getPanchayatById(panchayatId: String): Flow<tblClusterList>
}
