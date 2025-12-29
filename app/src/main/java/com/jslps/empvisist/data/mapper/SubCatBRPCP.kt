package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstSubCatBRPCP
import com.jslps.empvisist.data.remote.dto.SubCatBRPCPDto
import com.jslps.empvisist.domain.model.SubCatBRPCP

fun SubCatBRPCPDto.toDomain(): SubCatBRPCP {
    return SubCatBRPCP(
        Cat_ID = Cat_ID,
        Control_ID = Control_ID,
        Dependency_Status = Dependency_Status ?: "",
        Sub_Cat_ID = Sub_Cat_ID,
        Sub_Cat_Name = Sub_Cat_Name ?: "",
        Sub_Cat_Status = Sub_Cat_Status
    )
}

fun SubCatBRPCP.toEntity() = tblmstSubCatBRPCP(
    Cat_ID = Cat_ID,
    Control_ID = Control_ID,
    Dependency_Status = Dependency_Status,
    Sub_Cat_ID = Sub_Cat_ID,
    Sub_Cat_Name = Sub_Cat_Name,
    Sub_Cat_Status = Sub_Cat_Status
)

fun List<SubCatBRPCPDto>.toDomainList(): List<SubCatBRPCP> {
    return map { it.toDomain() }
}

fun List<SubCatBRPCP>.toEntityList() = map { it.toEntity() }

