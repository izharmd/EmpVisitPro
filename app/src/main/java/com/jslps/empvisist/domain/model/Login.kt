package com.jslps.empvisist.domain.model

import java.io.Serializable

data class Login(
    val userId: Int = 0,
    val districtcode: String?,
    val bloackcode: String,
    val clustercode: String,
    val villagecode: String,
    val CRPType: String,
    val username: String,
    val password: String,
    val CRPName: String,
    val mobilenumber: String,
    val VOAlloted: String,
    val UserLevel: String,
    val vocode: String,
    val isactive: String,
    val CreatedDate: String,
    val CreatedBy: String,
    val UpdatedDate: String,
    val UpdatedBy: String,
    val CLFcode: Int = 0,
    val Panchayat: String,
    val PanchayatCode: String,
    val DistrictName: String,
    val DistrictName_H: String,
    val BlockName: String,
    val BlockName_H: String,
    val lokos_code: String,
    val CFLName: String?
) : Serializable