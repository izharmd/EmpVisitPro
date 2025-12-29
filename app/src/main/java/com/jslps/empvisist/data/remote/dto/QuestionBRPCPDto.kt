package com.jslps.empvisist.data.remote.dto

import java.io.Serializable


data class QuestionBRPCPDto(
    val Cat_ID: Int = 0,
    val Control_ID: Int = 0,
    val Dependency_Status: String? = "",
    val Q_ID: Int = 0,
    val Q_Status: Boolean = false,
    val Question: String = "",
    val Sub_Cat_ID: Int = 0,
    val Sub_Cat_ID_C: Int = 0,
    val DependencyQID:String? = "",
    var Answer:String? = ""
): Serializable