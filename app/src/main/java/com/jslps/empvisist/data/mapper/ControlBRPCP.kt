package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstControlBRPCP
import com.jslps.empvisist.data.remote.dto.ControlBRPCPDto
import com.jslps.empvisist.domain.model.ControlBRPCP

fun ControlBRPCPDto.toDomain(): ControlBRPCP {
    return ControlBRPCP(
        C_Name = C_Name ?: "",
        C_Status = C_Status ?: false,
        Control_ID = Control_ID ?: 0
    )
}

fun ControlBRPCP.toEntity() = tblmstControlBRPCP(
    C_Name = C_Name,
    C_Status = C_Status,
    Control_ID = Control_ID
)

fun List<ControlBRPCPDto>.toDomainList(): List<ControlBRPCP> {
    return map { it.toDomain() }
}

fun List<ControlBRPCP>.toEntityList() = map { it.toEntity() }