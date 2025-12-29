package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val Cont_ID = 0
@Entity(indices = [Index(value = ["Control_ID"], unique = true)])
data class tblmstControlBRPCP(
    val C_Name: String,
    val C_Status: Boolean,
    val Control_ID: Int
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var primaryKeyControlId: Int = Cont_ID
}