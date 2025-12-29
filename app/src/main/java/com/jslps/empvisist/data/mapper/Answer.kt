package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblAnswer
import com.jslps.empvisist.data.remote.dto.AnswerDto
import com.jslps.empvisist.data.remote.dto.LoginDto
import com.jslps.empvisist.domain.model.Answer
import com.jslps.empvisist.domain.model.Login

fun AnswerDto.toDomain(): Answer {
    return Answer(
        categoryId = categoryId ?: "",
        subCategoryId = subCategoryId ?: "",
        chileCategoryId = chileCategoryId ?: "",
        createdOn = createdOn ?: "",
        createdDate = createdDate ?: "",
        questionId = questionId ?: "",
        answer = answer ?: "",
        isUpdatedOn = isUpdatedOn ?: "",
        isUpdated = isUpdated ?: "",
        createdBy = createdBy ?: "",
        isExported = isExported ?: 0,
        uuid = uuid ?: "",
        userId = userId ?: "",
        Amount = Amount ?: "",
        subCategoryCode = subCategoryCode ?: "",
        subCategoryValue = subCategoryValue ?: "",
        childCategoryCode = childCategoryCode ?: "",
        childCategoryValue = childCategoryValue ?: "",
        subCatInputValue = subCatInputValue ?: "",
        childCatInputValue = childCatInputValue ?: "",
        entryNumber = entryNumber ?: "",
        quesToBeAdd = quesToBeAdd ?: "",
        districtcode = districtcode ?: "",
        blockcode = blockcode ?: "",
        clustercode = clustercode ?: "",
        villagecode = villagecode ?: "",
        latitude = latitude ?: "",
        longitude = longitude ?: "",
        optionsIds = optionsIds ?: "",
        uuidParent = uuidParent ?: "",
    )
}

fun Answer.toEntity() = tblAnswer(
    categoryId = categoryId,
    subCategoryId = subCategoryId,
    chileCategoryId = chileCategoryId,
    createdOn = createdOn,
    createdDate = createdDate,
    questionId = questionId,
    answer = answer,
    isUpdatedOn = isUpdatedOn,
    isUpdated = isUpdated,
    createdBy = createdBy,
    isExported = isExported,
    uuid = uuid,
    userId = userId,
    Amount = Amount,
    subCategoryCode = subCategoryCode,
    subCategoryValue = subCategoryValue,
    childCategoryCode = childCategoryCode,
    childCategoryValue = childCategoryValue,
    subCatInputValue = subCatInputValue,
    childCatInputValue = childCatInputValue,
    entryNumber = entryNumber,
    quesToBeAdd = quesToBeAdd,
    districtcode = districtcode,
    blockcode = blockcode,
    clustercode = clustercode,
    villagecode = villagecode,
    latitude = latitude,
    longitude = longitude,
    optionsIds = optionsIds,
    uuidParent = uuidParent
)

fun List<AnswerDto>.toDomainList(): List<Answer> {
    return map { it.toDomain() }
}

fun List<Answer>.toEntityList() = map { it.toEntity() }

