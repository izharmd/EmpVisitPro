package com.jslps.empvisist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.data.local.entites.tblDailyAttendance
import com.jslps.empvisist.data.local.entites.tblSelfDeclMonthly
import com.jslps.empvisist.data.local.entites.tblVillageList
import com.jslps.empvisist.data.local.entites.tblAnswer
import com.jslps.empvisist.data.local.entites.tblHalfDay
import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.data.local.entites.tblWorkLimit
import com.jslps.empvisist.data.local.entites.tblmstCategoryBRPCP
import com.jslps.empvisist.data.local.entites.tblmstControlBRPCP
import com.jslps.empvisist.data.local.entites.tblmstOptionBRPCP
import com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP
import com.jslps.empvisist.data.local.entites.tblmstSubCatBRPCP
import com.jslps.empvisist.data.local.entites.tblmstSubCatCBRPCP
import com.jslps.empvisist.domain.model.CategoryBRPCP
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.model.DailyAttendance
import com.jslps.empvisist.domain.model.Login
import com.jslps.empvisist.domain.model.OptionBRPCP
import com.jslps.empvisist.domain.model.QuestionBRPCP
import com.jslps.empvisist.domain.model.SubCatBRPCP
import com.jslps.empvisist.domain.model.SubCatCBRPCP
import com.jslps.empvisist.domain.model.VillageList
import com.jslps.empvisist.domain.model.WorkLimit
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveUserLoginData(userList: List<com.jslps.empvisist.data.local.entites.tblLogin>): Array<Long>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveUserLoginDataNew(userList: List<com.jslps.empvisist.data.local.entites.tblLogin>): Array<Long>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstCategoryBRPCP(categoryList: List<com.jslps.empvisist.data.local.entites.tblmstCategoryBRPCP>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstClusterList(clusterList: List<tblClusterList>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstVillageList(villageList: List<com.jslps.empvisist.data.local.entites.tblVillageList>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstAnswerList(answerList: List<com.jslps.empvisist.data.local.entites.tblAnswer>): Array<Long>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstWorkLimitList(workListList: List<com.jslps.empvisist.data.local.entites.tblWorkLimit>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstAttendanceList(attendanceList: List<com.jslps.empvisist.data.local.entites.tblDailyAttendance>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savettblmstControlBRPCP(controlList: List<com.jslps.empvisist.data.local.entites.tblmstControlBRPCP>): Array<Long>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savettblmstQuestionBRPCP(quesionlList: List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstSubCatBRPCP(subCatList: List<com.jslps.empvisist.data.local.entites.tblmstSubCatBRPCP>): Array<Long>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstSub_Cat_C_BRPCP(subCatCList: List<com.jslps.empvisist.data.local.entites.tblmstSubCatCBRPCP>): Array<Long>



    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun savetblmstOptionBRPCP(optionList: List<com.jslps.empvisist.data.local.entites.tblmstOptionBRPCP>): Array<Long>


    @Insert
    suspend fun saveAnswerData(answerList: List<com.jslps.empvisist.data.local.entites.tblAnswer>)


    @Query("UPDATE  tblAnswer SET answer =:answer, subCatInputValue =:subCatInputValue,childCatInputValue=:childCatInputValue, isUpdated= :isUpdated,createdOn=:isUpdatedOn WHERE questionId = :questionId and categoryId =:catId and subCategoryId=:subCatId and chileCategoryId=:subCatChild and userId =:userId")
    suspend fun updateQuestionDataByQuestionId(
        answer: String,
        subCatInputValue: String,
        childCatInputValue: String,
        isUpdated: String,
        questionId: String,
        isUpdatedOn: String,
        catId: String,
        subCatId: String,
        subCatChild: String,
        userId: String,
    )


    @Query("SELECT * from tblmstCategoryBRPCP where Daily=:dailyId ")
    suspend fun getCategoryList(dailyId: String): List<com.jslps.empvisist.data.local.entites.tblmstCategoryBRPCP>

    @Query("SELECT * from tblClusterList")
    suspend fun getClusterList(): List<com.jslps.empvisist.data.local.entites.tblClusterList>

    @Query("SELECT * from tblClusterList")
    fun getPanchayatList(): Flow<List<com.jslps.empvisist.data.local.entites.tblClusterList>>

    @Query("SELECT * from tblVillageList where PanchayatCode=:clusterCode")
    suspend fun getVillageList(clusterCode: String): List<tblVillageList>

    @Query("SELECT * from tblVillageList where PanchayatCode=:clusterCode")
     fun getVillageList1(clusterCode: String): Flow<List<tblVillageList>>


    @Query("SELECT * from tblWorkLimit where workDate=:workDate and userId=:userId")
    suspend fun getWorkLimit(workDate: String,userId: String): List<com.jslps.empvisist.data.local.entites.tblWorkLimit>

    @Query("SELECT * from tblWorkLimit where workDate=:workDate and userId=:userId and subCatId=:subCatId")
    suspend fun getWorkLimitBySubCatId(workDate: String,userId: String,subCatId: Int): com.jslps.empvisist.data.local.entites.tblWorkLimit

    @Query("SELECT SUM(workLimit) FROM tblWorkLimit WHERE userId = :userId AND workDate = :workDate")
    suspend fun sumWorkLimit(workDate: String,userId: String): Int

    @Query("DELETE FROM tblWorkLimit WHERE userId=:userId AND workDate=:workDate AND subCatId=:subCatId")
    suspend fun deleteWorkLimit(workDate: String,userId: String,subCatId: Int)

    @Query("SELECT * from tblHalfDay where workDate=:workDate and userId=:userId")
    suspend fun getHalfDay(workDate: String,userId:String): List<com.jslps.empvisist.data.local.entites.tblHalfDay>

    @Query("UPDATE  tblWorkLimit SET workLimit =:workLimit WHERE workDate = :workDate and userId=:userId")
    suspend fun updateWorkLImit(workLimit: Int,workDate: String,userId: String)

    @Insert
    suspend fun saveWorkLimit(workList: com.jslps.empvisist.data.local.entites.tblWorkLimit)

    @Insert
    suspend fun saveHalfDay(halfDayList: com.jslps.empvisist.data.local.entites.tblHalfDay)

    @Query("SELECT * FROM tblAnswer where questionId = '1542' and createdDate=:createdDate")
    suspend fun getTotalServeyByIdAndDate(createdDate: String) :List<com.jslps.empvisist.data.local.entites.tblAnswer>



    @Query("SELECT * from tblAnswer where categoryId=:categoryId and subCategoryId=:subCategoryId and chileCategoryId =:chileCategoryId and questionId =:questionId  and createdDate =:createdDate and isExported = '0' and userId =:userId")
    suspend fun getAnswerDataById(
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,
        questionId: String,
        createdDate: String,
        userId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("SELECT * from tblAnswer where categoryId=:categoryId and subCategoryId=:subCategoryId and chileCategoryId =:chileCategoryId and questionId =:questionId and isExported = '0' and userId =:userId")
    suspend fun getAnswerDataById_Monthly(
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,
        questionId: String,
        userId: String
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>


    @Query("SELECT * from tblAnswer where categoryId=:categoryId and subCategoryId=:subCategoryId and chileCategoryId =:chileCategoryId and subCategoryCode ='9999' and userId =:userId")
    suspend fun getSubCategoryOtherById(
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,
        userId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>


    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:categoryId and Sub_Cat_ID=:subCategoryId and DependencyQID =:questionId and Q_Status = '1'")
    suspend fun getDependencyQuesById(
        categoryId: String,
        subCategoryId: String,
        questionId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * from tblmstQuestionBRPCP where DependencyQID =:questionId and Q_Status = '1'")
    suspend fun getDependencyQuesByQid(
        questionId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * FROM tblmstQuestionBRPCP WHERE DependencyQID IN (:questionIds) and Q_Status = '1'")
    suspend fun getDependencyQuesByQidMultiple(
        questionIds: List<String>,
    ): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * from tblmstOptionBRPCP where Cat_ID =:categotyId and Q_ID =:questionId and O_Status = '1'")
    suspend fun getOptionDataByQuestionID_1(
        categotyId: String,
        questionId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblmstOptionBRPCP>

    //use for if data is available in table then update button will show
    @Query("SELECT * from tblAnswer where categoryId=:categoryId and subCategoryId=:subCategoryId and chileCategoryId =:chileCategoryId and createdDate =:createdDate and userId =:userId and villagecode=:villageCode and isExported = '0'")
    suspend fun getAnswerDataByCategoryId(
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,
        createdDate: String,
        userId: String,
        villageCode:String
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("SELECT * from tblAnswer where categoryId=:categoryId and subCategoryId=:subCategoryId and chileCategoryId =:chileCategoryId  and userId =:userId and isExported = '0'")
    suspend fun getAnswerDataByCategoryId_1(
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,

        userId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>


    @Query("SELECT * from tblmstCategoryBRPCP")
    suspend fun getAllCategoryList(): List<com.jslps.empvisist.data.local.entites.tblmstCategoryBRPCP>

    @Query("SELECT * from tblLogin")
     fun getUserData(): Flow<tblLogin>





    @Query("SELECT * from tblmstSubCatBRPCP where Cat_ID=:catId ")
    suspend fun getSubCategoryList(catId: Int): List<com.jslps.empvisist.data.local.entites.tblmstSubCatBRPCP>

    @Query("SELECT * from tblmstSubCatCBRPCP where Cat_ID=:catId and Sub_Cat_ID_P =:sub_c_id ")
    suspend fun getSub_C_CategoryList(catId: Int, sub_c_id: Int): List<com.jslps.empvisist.data.local.entites.tblmstSubCatCBRPCP>

    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId  and Q_Status = '1'")
    suspend fun getQuestionListByCategoryId(catId: Int): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId and Sub_Cat_ID =:subCatId and Q_Status = '1'")
    suspend fun getQuestionListBySubCatId(catId: Int, subCatId: Int): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>


    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId and Sub_Cat_ID =:subCatId and Sub_Cat_ID_C =:sub_c_id and Q_Status = '1'")
    suspend fun getQuestionList(catId: Int, subCatId: Int, sub_c_id: Int): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>


    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId and Sub_Cat_ID =:subCatId and Sub_Cat_ID_C =:sub_c_id and DependencyQID IS NULL and Q_Status = '1' ")
    suspend fun getQuestionListCategory_1(
        catId: Int,
        subCatId: Int,
        sub_c_id: Int,
    ): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId and Sub_Cat_ID =:subCatId and Sub_Cat_ID_C =:sub_c_id and Dependency_Status = '1' and Q_Status = '1'")
    suspend fun getQuestionListDependancy(
        catId: Int,
        subCatId: Int,
        sub_c_id: Int,
    ): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>

    @Query("SELECT * from tblmstQuestionBRPCP where Cat_ID=:catId and Sub_Cat_ID =:subCatId and Q_Status = '1'")
    suspend fun getQuestionList_2(catId: Int, subCatId: Int): List<com.jslps.empvisist.data.local.entites.tblmstQuestionBRPCP>



    @Query("UPDATE  tblAnswer SET answer =:answer WHERE categoryId = :categoryId and subCategoryId=:subCategoryId and chileCategoryId=:chileCategoryId and questionId=:questionId and userId =:userId")
    suspend fun updateAnswer(
        answer: String,
        categoryId: String,
        subCategoryId: String,
        chileCategoryId: String,
        questionId: String,
        userId: String,
    )


    @Query("UPDATE  tblAnswer SET isExported ='1' WHERE questionId =:questionId and uuid = :uuid and userId =:userId")
    suspend fun updateAnswerTableByQuestionBYUUID(questionId: String, uuid: String, userId: String)


    @Query("SELECT * from tblAnswer where userId =:userId and isExported = '0'")
    suspend fun getAllAnswer(userId: String): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("SELECT * from tblAnswer where userId =:userId and createdOn<=:createdOn and isExported = '0'")
    suspend fun getAllAnswerByScheduler(userId: String,createdOn:String): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("SELECT * from tblAnswer where questionId = :questionId and categoryId =:categoryId and subCategoryId =:subCategoryId and chileCategoryId =:sub_c_id and userId =:userId")
    suspend fun getAnswerByID(
        questionId: String,
        categoryId: String,
        subCategoryId: String,
        sub_c_id: String,
        userId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    //  @Query("SELECT * from tblAnswer where createdDate=:createdDate and categoryId = '1' or categoryId = '2' and userId=:userId")
    @Query("SELECT * from tblAnswer where createdDate=:createdDate and userId=:userId")
    suspend fun getAnswerDataByCategoryID_Date(
        createdDate: String,
        userId: String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("select *  from tblAnswer where userId =:userId and  categoryId =:categoryId and isExported = '1' and createdDate between :startDate and :endDate")
    suspend fun getMonthlyData(
        userId: String,
        categoryId:String,
        startDate: String,
        endDate:String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("SELECT * FROM tblDailyAttendance WHERE userId=:userId and attendanceDate >=:date1  AND attendanceDate <=:date2 and isExported = '0'")
    suspend fun getAttendanceDate(
        userId: String,
        date1: String,
        date2: String,
    ): List<com.jslps.empvisist.data.local.entites.tblDailyAttendance>

    @Query("select *  from tblAnswer where userId =:userId and  categoryId =:categoryId and isExported = '0' and createdDate between :startDate and :endDate")
    suspend fun getMonthlyData_1(
        userId: String,
        categoryId:String,
        startDate: String,
        endDate:String,
    ): List<com.jslps.empvisist.data.local.entites.tblAnswer>

    @Query("DELETE from tblmstCategoryBRPCP")
    suspend fun deleteCategory()

    @Query("DELETE from tblmstOptionBRPCP")
    suspend fun deleteOptionBRPCP()
    @Query("DELETE from tblmstQuestionBRPCP")
    suspend fun deleteQuestionBRPCP()
    @Query("DELETE from tblmstSubCatBRPCP")
    suspend fun deleteSubCatBRPCP()
    @Query("DELETE from tblmstSubCatCBRPCP")
    suspend fun deleteSubCatCBRPCP()

    @Query("DELETE from tblLogin")
    suspend fun deleteUserData()

    //DELETE ANSWER DATA WHEN DATA IS UPLOADED ON SERVER
    //DELETE ANSWER DATA WHEN DATA IS UPLOADED ON SERVER
    @Query("delete from tblAnswer WHERE questionId =:questionId and uuid = :uuid and userId =:userId")
    suspend fun deleteAnswerDataByUserId(questionId:String,uuid:String,userId:String)

    @Query("delete from tblAnswer WHERE entryNumber =:entryNo  and userId =:userId and isExported = '0'")
    suspend fun deleteAnswerData(entryNo: String, userId: String)

    @Query("SELECT * from tblSelfDeclMonthly WHERE month=:month and year=:year and userId=:userId")
    suspend fun getMonthlyDeclaration(month:String,year:String,userId:Int): List<com.jslps.empvisist.data.local.entites.tblSelfDeclMonthly>

    @Query("SELECT * from tblSelfDeclMonthly WHERE userId=:userId")
    suspend fun getMonthlyDeclarationData(userId:Int): List<com.jslps.empvisist.data.local.entites.tblSelfDeclMonthly>

    @Query("SELECT * from tblDailyAttendance WHERE userId=:userId and attendanceDate=:date")
    suspend fun getDailyAttendance(userId:Int,date:String): List<com.jslps.empvisist.data.local.entites.tblDailyAttendance>


    @Query("SELECT * from tblDailyAttendance WHERE userId=:userId and attendanceDate BETWEEN :currentDate AND :previousDate ORDER BY attendanceDate DESC")
    suspend fun getAttendanceDateRange(userId: Int, currentDate: String,previousDate:String): List<com.jslps.empvisist.data.local.entites.tblDailyAttendance>


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveMonthlyDeclaration(arrList: ArrayList<com.jslps.empvisist.data.local.entites.tblSelfDeclMonthly>):Array<Long>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveDailyAttendanceData(model: com.jslps.empvisist.data.local.entites.tblDailyAttendance):Long
}