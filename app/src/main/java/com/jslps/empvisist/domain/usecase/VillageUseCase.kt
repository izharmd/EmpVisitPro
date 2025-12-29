package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.domain.repository.VillageRepository
import javax.inject.Inject

class VillageUseCase @Inject constructor(
    private val villageRepository: VillageRepository
){
     fun getVillageList(clusterCode: String) = villageRepository.getVillageList1(clusterCode)

}