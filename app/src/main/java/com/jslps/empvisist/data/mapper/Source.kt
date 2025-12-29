package com.jslps.empvisist.data.mapper
import com.jslps.empvisist.data.remote.dto.SourceDto
import com.jslps.empvisist.domain.model.Source

fun SourceDto.toDomain(): Source {
    return Source(
        id = id,
        name = name
    )
}

fun List<SourceDto>.toDomainList(): List<Source> {
    return map { it.toDomain() }
}