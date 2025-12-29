package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [Index(value = ["userId","month","year","workFieldId"], unique = true)])
data class tblSelfDeclMonthly(
    val userId: Int? = 0,
    val username: String? = "",
    val districtcode: String? = "",
    val bloackcode: String? = "",
    val clustercode: String? = "",
    val villagecode:String? = "",
    var month: String? = "",
    var year:String? = "",
    var uuid: String? = "",
    var noOfField:String? = "",
    var workFieldName: String? = "",
    var createdDate: String? = "",
    var workFieldId: Int? = 0,
    var isExported: Int? = 0
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var selfDeclId: Int = 0
}
