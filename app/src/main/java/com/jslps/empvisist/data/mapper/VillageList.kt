package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblVillageList
import com.jslps.empvisist.data.remote.dto.VillageListDto
import com.jslps.empvisist.domain.model.VillageList

fun VillageListDto.toDomain(): VillageList {
    return VillageList(
        ClfCode = ClfCode ?: "",
        PanchayatCode = PanchayatCode ?: "",
        LgdVillage = LgdVillage ?: "",
        VillageCode = VillageCode,
        VillageId = VillageId ?: "",
        VillageName = VillageName ?: ""
    )
}

fun VillageList.toEntity() = tblVillageList(
    ClfCode = ClfCode,
    PanchayatCode = PanchayatCode,
    LgdVillage = LgdVillage,
    VillageCode = VillageCode,
    VillageId = VillageId,
    VillageName = VillageName
)

fun List<VillageListDto>.toDomainList(): List<VillageList> {
    return map { it.toDomain() }
}

fun List<VillageList>.toEntityList() = map { it.toEntity() }