package com.jslps.empvisist.domain.model

import java.io.Serializable


data class SubCatBRPCP(
    val Cat_ID: Int,
    val Control_ID: Int,
    val Dependency_Status: String,
    val Sub_Cat_ID: Int,
    val Sub_Cat_Name: String,
    val Sub_Cat_Status: Boolean
): Serializable