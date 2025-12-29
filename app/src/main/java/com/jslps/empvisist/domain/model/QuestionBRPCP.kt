package com.jslps.empvisist.domain.model

import java.io.Serializable


data class QuestionBRPCP(
    val Cat_ID: Int,
    val Control_ID: Int,
    val Dependency_Status: String,
    val Q_ID: Int ,
    val Q_Status: Boolean ,
    val Question: String,
    val Sub_Cat_ID: Int,
    val Sub_Cat_ID_C: Int ,
    val DependencyQID:String,
    var Answer:String
): Serializable