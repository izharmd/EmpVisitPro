package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstSubCatCBRPCP
import com.jslps.empvisist.data.remote.dto.SubCatCBRPCPDto
import com.jslps.empvisist.domain.model.SubCatCBRPCP

fun SubCatCBRPCPDto.toDomain(): SubCatCBRPCP {
    return SubCatCBRPCP(
        Cat_ID = Cat_ID,
        Control_ID = Control_ID,
        Dependency_Status = Dependency_Status ?: "",
        Sub_Cat_ID_C = Sub_Cat_ID_C,
        Sub_Cat_ID_P = Sub_Cat_ID_P,
        Sub_Cat_Name = Sub_Cat_Name ?: "",
        Sub_Status_C = Sub_Status_C
    )
}

fun SubCatCBRPCP.toEntity() = tblmstSubCatCBRPCP(
    Cat_ID = Cat_ID,
    Control_ID = Control_ID,
    Dependency_Status = Dependency_Status,
    Sub_Cat_ID_C = Sub_Cat_ID_C,
    Sub_Cat_ID_P = Sub_Cat_ID_P,
    Sub_Cat_Name = Sub_Cat_Name,
    Sub_Status_C = Sub_Status_C
)

fun List<SubCatCBRPCPDto>.toDomainList(): List<SubCatCBRPCP> {
    return map { it.toDomain() }
}

fun List<SubCatCBRPCP>.toEntityList() = map { it.toEntity() }

