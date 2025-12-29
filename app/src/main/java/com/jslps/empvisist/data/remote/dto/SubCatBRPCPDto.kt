package com.jslps.empvisist.data.remote.dto

import java.io.Serializable


data class SubCatBRPCPDto(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val Sub_Cat_ID: Int = 0,
    val Sub_Cat_Name: String? = "",
    val Sub_Cat_Status: Boolean = false

): Serializable