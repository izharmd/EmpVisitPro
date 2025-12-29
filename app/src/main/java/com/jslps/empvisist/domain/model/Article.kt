package com.jslps.empvisist.domain.model

@kotlinx.serialization.Serializable
data class Article(
    val source: Source? = Source(),
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? ="",
    val publishedAt: String? ="",
    val content: String? = ""
)