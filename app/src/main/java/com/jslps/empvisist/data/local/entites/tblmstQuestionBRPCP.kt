package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


const val ID = 0
@Entity(indices = [Index(value = ["Q_ID"], unique = true)])
data class tblmstQuestionBRPCP(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val Q_ID: Int = 0,
    val Q_Status: Boolean = false,
    val Question: String = "",
    val Sub_Cat_ID: Int = 0,
    val Sub_Cat_ID_C: Int = 0,
    val DependencyQID:String? = "",
    var Answer:String? = ""
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var primaryKeyQuestionId: Int = ID
}