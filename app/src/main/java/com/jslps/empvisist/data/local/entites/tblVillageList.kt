package com.jslps.empvisist.data.local.entites

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [Index(value = ["VillageCode"], unique = true)])
data class tblVillageList(
    val ClfCode: String? = "",
    val PanchayatCode: String? = "",
    val LgdVillage: String? = "",
    @PrimaryKey
    @NonNull
    val VillageCode: String = "",
    val VillageId: String? = "",
    val VillageName: String? = ""
): Serializable {
    override fun toString(): String {
        return VillageName.toString()
    }
}