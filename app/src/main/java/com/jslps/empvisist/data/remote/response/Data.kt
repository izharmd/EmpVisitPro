package com.jslps.empvisist.data.remote.response

import com.jslps.empvisist.data.local.entites.tblDailyAttendance
import com.jslps.empvisist.data.remote.dto.AnswerDto
import com.jslps.empvisist.data.remote.dto.CategoryBRPCPDto
import com.jslps.empvisist.data.remote.dto.ClusterListDto
import com.jslps.empvisist.data.remote.dto.LoginDto
import com.jslps.empvisist.data.remote.dto.OptionBRPCPDto
import com.jslps.empvisist.data.remote.dto.QuestionBRPCPDto
import com.jslps.empvisist.data.remote.dto.SubCatBRPCPDto
import com.jslps.empvisist.data.remote.dto.SubCatCBRPCPDto
import com.jslps.empvisist.data.remote.dto.VillageListDto
import com.jslps.empvisist.data.remote.dto.WorkLimitDto
import com.jslps.empvisist.domain.model.DailyAttendance
import java.io.Serializable

data class Data(
    val CategoryList: List<CategoryBRPCPDto>?= arrayListOf(),
    val OptionListList: List<OptionBRPCPDto>?= arrayListOf(),
    val QuestionList: List<QuestionBRPCPDto>?= arrayListOf(),
    val SubCategoryList: List<SubCatBRPCPDto>?= arrayListOf(),
    val SubCategory_C_List: List<SubCatCBRPCPDto>?= arrayListOf(),
    val userData: List<LoginDto>?= arrayListOf(),
    val ClusterList: List<ClusterListDto>?= arrayListOf(),
    val VillageList: List<VillageListDto>?= arrayListOf(),
    val workLimitList: List<WorkLimitDto>?= arrayListOf(),
    val answerList: List<AnswerDto>?= arrayListOf(),
    val attenddanceList: List<DailyAttendance>?= arrayListOf()
): Serializable