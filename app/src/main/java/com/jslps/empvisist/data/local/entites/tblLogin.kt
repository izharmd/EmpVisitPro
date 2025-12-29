package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

const val userId1 = 0

@Entity(indices = [Index(value = ["userId"], unique = true)])
data class tblLogin(
    val userId: Int = 0,
    val districtcode: String? = "",
    val bloackcode: String? = "",
    val clustercode: String? = "",
    val villagecode: String? = "",
    val CRPType: String? = "",
    val username: String? = "",
    val password: String? = "",
    val CRPName: String? = "",
    val mobilenumber: String? = "",
    val VOAlloted: String? = "",
    val UserLevel: String? = "",
    val vocode: String? = "",
    val isactive: String? = "",
    val CreatedDate: String? = "",
    val CreatedBy: String? = "",
    val UpdatedDate: String? = "",
    val UpdatedBy: String? = "",
    val CLFcode: Int = 0,
    val Panchayat: String? = "",
    val PanchayatCode: String? = "",
    val DistrictName: String? = "",
    val DistrictName_H: String? = "",
    val BlockName: String? = "",
    val BlockName_H: String? = "",
    val lokos_code: String? = "",
    val CFLName: String? = ""
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var ids: Int = userId1
}