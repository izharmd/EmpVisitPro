package com.jslps.empvisist.data.remote.dto

import java.io.Serializable


data class WorkLimitDto(
    val workLimit: Int? = 0,
    val workDate:String? = "",
    val userId:String? = "",
    val subCatId:Int? = 0,
    var appVersion:String? = ""
): Serializable
