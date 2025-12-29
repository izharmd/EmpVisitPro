package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable



//@Entity(indices = [Index(value = ["userId", "workDate"], unique = true)])
@Entity
data class tblWorkLimit(
    val workLimit: Int,
    val workDate:String,
    val userId:String,
    val subCatId:Int? = 0,
    var appVersion:String? = ""
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
