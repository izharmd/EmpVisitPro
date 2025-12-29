package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.data.remote.dto.ClusterListDto
import com.jslps.empvisist.domain.model.ClusterList

fun ClusterListDto.toDomain(): ClusterList {
    return ClusterList(
        ClfCode = ClfCode ?: "",
        PanchayatId = PanchayatId ?: "",
        LgdGp = LgdGp ?: "",
        PanchayatCode = PanchayatCode ?: "",
        PanchayatName = PanchayatName ?: ""
    )
}

fun ClusterList.toEntity() = tblClusterList(
    ClfCode = ClfCode,
    PanchayatId = PanchayatId,
    LgdGp = LgdGp,
    PanchayatCode = PanchayatCode,
    PanchayatName = PanchayatName
)


fun List<ClusterListDto>.toDomainList(): List<ClusterList> {
    return map { it.toDomain() }
}

fun List<ClusterList>.toEntityList() = map { it.toEntity() }