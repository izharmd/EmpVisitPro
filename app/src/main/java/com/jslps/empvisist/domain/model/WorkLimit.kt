package com.jslps.empvisist.domain.model

import java.io.Serializable


data class WorkLimit(
    val workLimit: Int,
    val workDate:String,
    val userId:String,
    val subCatId:Int,
    var appVersion:String
): Serializable
