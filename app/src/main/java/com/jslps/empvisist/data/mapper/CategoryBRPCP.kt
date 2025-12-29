package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstCategoryBRPCP
import com.jslps.empvisist.data.remote.dto.CategoryBRPCPDto
import com.jslps.empvisist.domain.model.CategoryBRPCP

fun CategoryBRPCPDto.toDomain(): CategoryBRPCP {
    return CategoryBRPCP(
        Cat_ControlID = Cat_ControlID ?: 0,
        Cat_ID = Cat_ID ?: 0,
        Cat_Name = Cat_Name ?: "",
        Cat_Status = Cat_Status ?: false,
        Monthly = Monthly ?: false,
        Daily = Daily ?: false,
        Cat_ImageBase64 = Cat_ImageBase64 ?: "",
        WorkLimit = WorkLimit ?: ""
    )
}

fun CategoryBRPCP.toEntity() = tblmstCategoryBRPCP(
    Cat_ControlID = Cat_ControlID,
    Cat_ID = Cat_ID,
    Cat_Name = Cat_Name,
    Cat_Status = Cat_Status,
    Monthly = Monthly,
    Daily = Daily,
    Cat_ImageBase64 = Cat_ImageBase64,
    WorkLimit = WorkLimit
)


fun List<CategoryBRPCPDto>.toDomainList(): List<CategoryBRPCP> {
    return map { it.toDomain() }
}

fun List<CategoryBRPCP>.toEntityList() = map { it.toEntity() }


