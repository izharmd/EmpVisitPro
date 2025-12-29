package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP
import com.jslps.empvisist.data.remote.dto.QuestionBRPCPDto
import com.jslps.empvisist.domain.model.QuestionBRPCP

fun QuestionBRPCPDto.toDomain(): QuestionBRPCP {
    return QuestionBRPCP(
        Cat_ID = Cat_ID,
        Control_ID = Control_ID,
        Dependency_Status = Dependency_Status ?: "",
        Q_ID = Q_ID,
        Q_Status = Q_Status,
        Question = Question,
        Sub_Cat_ID = Sub_Cat_ID,
        Sub_Cat_ID_C = Sub_Cat_ID_C,
        DependencyQID = DependencyQID ?: "",
        Answer = Answer ?: ""
    )
}

fun QuestionBRPCP.toEntity() = tblmstQuestionBRPCP(
    Cat_ID = Cat_ID,
    Control_ID = Control_ID,
    Dependency_Status = Dependency_Status,
    Q_ID = Q_ID,
    Q_Status = Q_Status,
    Question = Question,
    Sub_Cat_ID = Sub_Cat_ID,
    Sub_Cat_ID_C = Sub_Cat_ID_C,
    DependencyQID = DependencyQID,
    Answer = Answer
)


fun List<QuestionBRPCPDto>.toDomainList(): List<QuestionBRPCP> {
    return map { it.toDomain() }
}

fun List<QuestionBRPCP>.toEntityList() = map { it.toEntity() }

