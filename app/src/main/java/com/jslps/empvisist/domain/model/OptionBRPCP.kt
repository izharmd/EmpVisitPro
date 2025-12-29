package com.jslps.empvisist.domain.model

import java.io.Serializable


data class OptionBRPCP(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String,
    val O_Name: String,
    val O_Status: Boolean? = false,
    val Option_ID: Int,
    val Q_ID: Int,
    val Sub_Cat_ID: String,
    val Sub_Cat_ID_C: String
): Serializable