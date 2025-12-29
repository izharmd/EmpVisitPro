package com.jslps.empvisist.data.local.repository

import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.local.entites.tblVillageList
import com.jslps.empvisist.domain.repository.VillageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VillageImpl @Inject constructor(
    private val dao: AppDao
): VillageRepository{
    override fun getVillageList1(clusterCode: String): Flow<List<tblVillageList>> {
        return dao.getVillageList1(clusterCode)
    }

}