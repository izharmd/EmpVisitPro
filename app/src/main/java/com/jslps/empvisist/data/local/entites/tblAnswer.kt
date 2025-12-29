package com.jslps.empvisist.data.local.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

const val ANSWERID = 0

@Entity(indices = [Index(value = ["uuid"], unique = true)])
data class tblAnswer(
    val categoryId: String = "",
    val subCategoryId: String = "",
    val chileCategoryId: String = "",
    val createdOn: String = "", // answer updated date time
    val createdDate: String = "",
    val questionId: String = "",
    var answer: String = "",
    val isUpdatedOn: String = "",
    val isUpdated: String = "",
    val createdBy: String = "",
    val isExported: Int = 0,
    val uuid: String = "",
    val userId: String = "",
    val Amount: String = "",

    val subCategoryCode: String = "",
    val subCategoryValue: String = "",
    val childCategoryCode: String = "",
    val childCategoryValue: String = "",

    val subCatInputValue: String = "",
    val childCatInputValue: String = "",

    val entryNumber: String = "",

    // New ====================
    val quesToBeAdd: String? = "",
    val districtcode: String? = "",
    val blockcode: String? = "",
    val clustercode: String? = "",
    val villagecode: String? = "",
    var latitude: String? = "",
    var longitude: String? = "",
    var optionsIds: String? = "",
    val uuidParent: String? = "",

    ) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var answerId: Int = ANSWERID
}