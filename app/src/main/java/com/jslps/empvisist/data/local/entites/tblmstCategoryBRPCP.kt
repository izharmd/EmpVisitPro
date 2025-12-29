package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val Cat_ID = 0
@Entity(indices = [Index(value = ["Cat_ID"], unique = true)])
data class tblmstCategoryBRPCP(
    val Cat_ControlID: Int? = 0,
    val Cat_ID: Int? = 0,
    val Cat_Name: String? = "",
    val Cat_Status: Boolean? = false,
    val Monthly:Boolean? = false,
    val Daily:Boolean? = false,
    val Cat_ImageBase64:String? = "",
    val WorkLimit:String? = ""
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var primaryKeyCatlId: Int = 0
}