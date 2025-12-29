package com.jslps.empvisist.domain.model

import java.io.Serializable

data class SubCatCBRPCP(
    val Cat_ID: Int,
    val Control_ID: Int,
    val Dependency_Status: String,
    val Sub_Cat_ID_C: Int,
    val Sub_Cat_ID_P: Int,
    val Sub_Cat_Name: String,
    val Sub_Status_C: Boolean
): Serializable