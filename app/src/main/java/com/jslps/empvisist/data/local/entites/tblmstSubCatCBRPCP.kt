package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val Sub_Cat_ID_Child = 0
@Entity(indices = [Index(value = ["Sub_Cat_ID_C"], unique = true)])
data class tblmstSubCatCBRPCP(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val Sub_Cat_ID_C: Int = 0,
    val Sub_Cat_ID_P: Int = 0,
    val Sub_Cat_Name: String? = "",
    val Sub_Status_C: Boolean = false
): Serializable {
    override fun toString(): String {
        return Sub_Cat_Name.toString()
    }
    @PrimaryKey(autoGenerate = true)
    var primaryKeySub_Cat_ID_C: Int = Sub_Cat_ID_Child
}