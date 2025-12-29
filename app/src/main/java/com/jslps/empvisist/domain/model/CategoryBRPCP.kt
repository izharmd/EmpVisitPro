package com.jslps.empvisist.domain.model

import java.io.Serializable


data class CategoryBRPCP(
    val Cat_ControlID: Int,
    val Cat_ID: Int,
    val Cat_Name: String,
    val Cat_Status: Boolean,
    val Monthly:Boolean,
    val Daily:Boolean,
    val Cat_ImageBase64:String,
    val WorkLimit:String?
): Serializable