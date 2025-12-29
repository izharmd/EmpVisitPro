package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblWorkLimit
import com.jslps.empvisist.data.remote.dto.VillageListDto
import com.jslps.empvisist.data.remote.dto.WorkLimitDto
import com.jslps.empvisist.domain.model.VillageList
import com.jslps.empvisist.domain.model.WorkLimit

fun WorkLimitDto.toDomain(): WorkLimit {
    return WorkLimit(
        workLimit = workLimit ?: 0,
        workDate = workDate ?: "",
        userId = userId ?: "",
        subCatId = subCatId ?: 0,
        appVersion = appVersion ?: ""
    )
}


fun WorkLimit.toEntity() = tblWorkLimit(
    workLimit = workLimit,
    workDate = workDate,
    userId = userId,
    subCatId = subCatId,
    appVersion = appVersion
)

fun List<WorkLimitDto>.toDomainList(): List<WorkLimit> {
    return map { it.toDomain() }
}

fun List<WorkLimit>.toEntityList() = map { it.toEntity() }

