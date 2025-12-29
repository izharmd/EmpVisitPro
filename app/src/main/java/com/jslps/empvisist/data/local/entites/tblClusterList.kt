package com.jslps.empvisist.data.local.entites

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(indices = [Index(value = ["PanchayatCode"], unique = true)])
data class tblClusterList(
    var ClfCode: String? = "",

    var PanchayatId: String = "",
    var LgdGp: String = "",
    @PrimaryKey
    @NonNull
    var PanchayatCode: String = "",
    var PanchayatName: String? = ""
): Serializable {
    override fun toString(): String {
        return PanchayatName.toString()
    }
}




