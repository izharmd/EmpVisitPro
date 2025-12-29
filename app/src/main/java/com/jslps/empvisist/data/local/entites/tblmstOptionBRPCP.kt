package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val Opt_ID = 0
@Entity(indices = [Index(value = ["Option_ID"], unique = true)])
data class tblmstOptionBRPCP(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val O_Name: String? = "",
    val O_Status: Boolean? = false,
    val Option_ID: Int? = 0,
    val Q_ID: Int? = 0,
    val Sub_Cat_ID: String? = "",
    val Sub_Cat_ID_C: String?= ""
): Serializable {
    override fun toString(): String {
        return this.O_Name.toString()
    }

    @PrimaryKey(autoGenerate = true)
    var primaryKeyOption_ID: Int = Opt_ID
}