package com.jslps.empvisist.domain.model

import java.io.Serializable

data class Answer(
    val categoryId: String,
    val subCategoryId: String,
    val chileCategoryId: String,
    val createdOn: String,
    val createdDate: String,
    val questionId: String,
    var answer: String,
    val isUpdatedOn: String,
    val isUpdated: String,
    val createdBy: String,
    val isExported: Int,
    val uuid: String,
    val userId: String,
    val Amount: String,

    val subCategoryCode: String,
    val subCategoryValue: String,
    val childCategoryCode: String,
    val childCategoryValue: String,

    val subCatInputValue: String,
    val childCatInputValue: String,

    val entryNumber: String,

    // New ====================
    val quesToBeAdd: String,
    val districtcode: String,
    val blockcode: String,
    val clustercode: String,
    val villagecode: String,
    var latitude: String,
    var longitude: String,
    var optionsIds: String,
    val uuidParent: String

    ) : Serializable