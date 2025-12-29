package com.jslps.empvisist.data.remote.dto

import java.io.Serializable

data class DailyAttendanceDto(
    val userId: Int? = 0,
    val username: String? = "",
    val districtcode: String? = "",
    val blockcode: String? = "",
    val clustercode: String? = "",
    var villagecode:String? = "",
    var month: String? = "",
    var year:String? = "",
    var date: String? = "",
    var markAttendanceId:Int? = 0,
    var markAttendance:String? = "",
    var attendanceDate: String? = "",

    var isDrpSrpTraining: String? = "",
    var isDrpSrpTrainingId: Int? = 0,
    var trainingSubject:String? = "",
    var trainingSubjectId:Int? = 0,
    var trainingFromDate:String? = "",
    var trainingToDate:String? = "",

    var createdDate: String? = "",
    var uuid: String? = "",
    var isExported: Int? = 0,
    var appVersion: String? = "",

    ): Serializable
