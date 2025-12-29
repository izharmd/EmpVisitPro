package com.jslps.empvisist.data.remote.dto

import java.io.Serializable

data class ControlBRPCPDto(
    val C_Name: String? = "",
    val C_Status: Boolean? = false,
    val Control_ID: Int? = 0
): Serializable