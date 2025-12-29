package com.jslps.empvisist.domain.repository

import com.jslps.empvisist.data.local.entites.tblVillageList
import kotlinx.coroutines.flow.Flow

interface VillageRepository{
     fun getVillageList1(clusterCode: String): Flow<List<tblVillageList>>
}