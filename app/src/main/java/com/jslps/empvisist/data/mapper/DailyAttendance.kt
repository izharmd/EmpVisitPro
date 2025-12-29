package com.jslps.empvisist.data.mapper

import com.jslps.empvisist.data.local.entites.tblDailyAttendance
import com.jslps.empvisist.data.remote.dto.DailyAttendanceDto
import com.jslps.empvisist.domain.model.DailyAttendance

fun DailyAttendanceDto.toDomain(): DailyAttendance {
    return DailyAttendance(
        userId = userId,
        username = username,
        districtcode = districtcode,
        blockcode = blockcode,
        clustercode = clustercode,
        villagecode = villagecode,
        month = month,
        year = year,
        date = date,
        markAttendanceId = markAttendanceId,
        markAttendance = markAttendance,
        attendanceDate = attendanceDate,
        isDrpSrpTraining = isDrpSrpTraining,
        isDrpSrpTrainingId = isDrpSrpTrainingId,
        trainingSubject = trainingSubject,
        trainingSubjectId = trainingSubjectId,
        trainingFromDate = trainingFromDate,
        trainingToDate = trainingToDate,
        createdDate = createdDate,
        uuid = uuid,
        isExported = isExported,
        appVersion = appVersion
    )
}

fun DailyAttendance.toEntity() = tblDailyAttendance(
    userId = userId,
    username = username,
    districtcode = districtcode,
    blockcode = blockcode,
    clustercode = clustercode,
    villagecode = villagecode,
    month = month,
    year = year,
    date = date,
    markAttendanceId = markAttendanceId,
    markAttendance = markAttendance,
    attendanceDate = attendanceDate,
    isDrpSrpTraining = isDrpSrpTraining,
    isDrpSrpTrainingId = isDrpSrpTrainingId,
    trainingSubject = trainingSubject,
    trainingSubjectId = trainingSubjectId,
    trainingFromDate = trainingFromDate,
    trainingToDate = trainingToDate,
    createdDate = createdDate,
    uuid = uuid,
    isExported = isExported,
    appVersion = appVersion
)

fun List<DailyAttendanceDto>.toDomainList(): List<DailyAttendance> {
    return map { it.toDomain() }
}

fun List<DailyAttendance>.toEntityList() = map { it.toEntity() }

