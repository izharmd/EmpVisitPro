package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.data.remote.dto.LoginDto
import com.jslps.empvisist.domain.model.Login

fun LoginDto.toDomain(): Login {
    return Login(
        userId = userId,
        districtcode = districtcode,
        bloackcode = bloackcode ?: "",
        clustercode = clustercode ?: "",
        villagecode = villagecode ?: "",
        CRPType = CRPType ?: "",
        username = username ?: "",
        password = password ?: "",
        CRPName = CRPName ?: "",
        mobilenumber = mobilenumber ?: "",
        VOAlloted = VOAlloted ?: "",
        UserLevel = UserLevel ?: "",
        vocode = vocode ?: "",
        isactive = isactive ?: "",
        CreatedDate = CreatedDate ?: "",
        CreatedBy = CreatedBy ?: "",
        UpdatedDate = UpdatedDate ?: "",
        UpdatedBy = UpdatedBy ?: "",
        CLFcode = CLFcode,
        Panchayat = Panchayat ?: "",
        PanchayatCode = PanchayatCode ?: "",
        DistrictName = DistrictName ?: "",
        DistrictName_H = DistrictName_H ?: "",
        BlockName = BlockName ?: "",
        BlockName_H = BlockName_H ?: "",
        lokos_code = lokos_code ?: "",
        CFLName = CFLName
    )
}


fun Login.toEntity() = tblLogin(
        userId = userId,
        districtcode = districtcode,
        bloackcode = bloackcode,
        clustercode = clustercode,
        villagecode = villagecode ,
        CRPType = CRPType ,
        username = username ,
        password = password ,
        CRPName = CRPName ,
        mobilenumber = mobilenumber ,
        VOAlloted = VOAlloted ,
        UserLevel = UserLevel ,
        vocode = vocode ,
        isactive = isactive ,
        CreatedDate = CreatedDate ,
        CreatedBy = CreatedBy ,
        UpdatedDate = UpdatedDate ,
        UpdatedBy = UpdatedBy ,
        CLFcode = CLFcode,
        Panchayat = Panchayat ,
        PanchayatCode = PanchayatCode ,
        DistrictName = DistrictName ,
        DistrictName_H = DistrictName_H ,
        BlockName = BlockName ,
        BlockName_H = BlockName_H ,
        lokos_code = lokos_code ,
        CFLName = CFLName

)

fun List<LoginDto>.toDomainList(): List<Login> {
    return map { it.toDomain() }
}

fun List<Login>.toEntityList() = map { it.toEntity() }