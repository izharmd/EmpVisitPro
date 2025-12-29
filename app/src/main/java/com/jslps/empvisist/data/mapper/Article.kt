package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.remote.dto.ArticleDto
import com.jslps.empvisist.domain.model.Article
import com.jslps.empvisist.domain.model.Source

fun ArticleDto.toDomain(): Article {
    return Article(
        source = source?.let { Source(it.id, it.name) },
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun List<ArticleDto>.toDomainList(): List<Article> {
    return map { it.toDomain() }
}