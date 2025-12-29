package com.jslps.empvisist.data.remote.response

data class ResponseData(
    val Message: String? = "",
    val `data`: List<Data> = arrayListOf(),
    val status: String? = "",
    val statusCode: Int? = 0
)