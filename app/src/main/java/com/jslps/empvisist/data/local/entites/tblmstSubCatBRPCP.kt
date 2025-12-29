package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val Sub_C_ID = 0
@Entity(indices = [Index(value = ["Sub_Cat_ID"], unique = true)])
data class tblmstSubCatBRPCP(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val Sub_Cat_ID: Int = 0,
    val Sub_Cat_Name: String? = "",
    val Sub_Cat_Status: Boolean = false

): Serializable {
    override fun toString(): String {
        return this.Sub_Cat_Name.toString() // What to display in the Spinner list.
    }
    @PrimaryKey(autoGenerate = true)
    var primaryKeySubCatId: Int = Sub_C_ID
}