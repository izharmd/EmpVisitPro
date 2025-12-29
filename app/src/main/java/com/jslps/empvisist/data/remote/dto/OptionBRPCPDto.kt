package com.jslps.empvisist.data.remote.dto

import java.io.Serializable


data class OptionBRPCPDto(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val O_Name: String? = "",
    val O_Status: Boolean? = false,
    val Option_ID: Int? = 0,
    val Q_ID: Int? = 0,
    val Sub_Cat_ID: String? = "",
    val Sub_Cat_ID_C: String?= ""
): Serializable