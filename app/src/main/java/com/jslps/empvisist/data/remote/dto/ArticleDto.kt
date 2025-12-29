package com.jslps.empvisist.data.remote.dto

@kotlinx.serialization.Serializable
data class ArticleDto(
    val source: SourceDto? = SourceDto(),
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? ="",
    val publishedAt: String? = "",
    val content: String? = "",
)