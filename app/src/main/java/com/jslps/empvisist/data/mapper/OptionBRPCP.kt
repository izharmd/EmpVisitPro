package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstOptionBRPCP
import com.jslps.empvisist.data.remote.dto.ControlBRPCPDto
import com.jslps.empvisist.data.remote.dto.OptionBRPCPDto
import com.jslps.empvisist.domain.model.ControlBRPCP
import com.jslps.empvisist.domain.model.OptionBRPCP

fun OptionBRPCPDto.toDomain(): OptionBRPCP {
    return OptionBRPCP(
        Cat_ID = Cat_ID,
        Control_ID = Control_ID,
        Dependency_Status = Dependency_Status ?: "",
        O_Name = O_Name ?: "",
        O_Status = O_Status ?: false,
        Option_ID = Option_ID ?: 0,
        Q_ID = Q_ID ?: 0,
        Sub_Cat_ID = Sub_Cat_ID ?: "",
        Sub_Cat_ID_C = Sub_Cat_ID_C ?: ""
    )
}

fun OptionBRPCP.toEntity() = tblmstOptionBRPCP(
    Cat_ID = Cat_ID,
    Control_ID = Control_ID,
    Dependency_Status = Dependency_Status,
    O_Name = O_Name,
    O_Status = O_Status,
    Option_ID = Option_ID,
    Q_ID = Q_ID,
    Sub_Cat_ID = Sub_Cat_ID,
    Sub_Cat_ID_C = Sub_Cat_ID_C
)

fun List<OptionBRPCPDto>.toDomainList(): List<OptionBRPCP> {
    return map { it.toDomain() }
}

fun List<OptionBRPCP>.toEntityList() = map { it.toEntity() }

