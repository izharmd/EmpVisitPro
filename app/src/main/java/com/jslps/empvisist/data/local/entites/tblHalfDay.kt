package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val dayId = 0
@Entity(indices = [Index(value = ["id"], unique = true)])
data class tblHalfDay(
val halfDay: Int,
val workDate:String,
val userId:String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = dayId
}
