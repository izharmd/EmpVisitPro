package com.jslps.compose.utils

import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.jslps.empvisist.data.local.entites.tblLogin
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Random
import java.util.UUID

class AppConstant {
    companion object {
        const val dbName = "BRPDATABASE"
        const val BASE_URL = "http://testservice.swalekha.in/Service.asmx/"
        const val imagePath = "https://jslpsupaj.s3.ap-south-1.amazonaws.com/UPAJ_Document/"


        const val loginAPI = "genderCRPUserLoginNew"
        const val uploadMonlhlyDataAPI = "uploadMonlhlyDataAPI"
       // const val uploaDailyAttendanceDataAPI = "uploaDailyAttendanceDataAPI"
        const val uploaDailyAttendanceDataAPI = "InsertDataforGenderCRPNew"
        const val GenderCRPFileUpload = "GenderCRPFileUpload"


        const val navigation_graph = "navigation_graph"

        var tblLogin: tblLogin? = null
        fun AppCompatActivity.callScreen() {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val rootView = findViewById<View>(android.R.id.content)
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
                val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                val bottomInset = maxOf(imeInsets.bottom, navBarInsets.bottom)
                v.setPadding(
                    systemBars.left, systemBars.top, systemBars.right,
                    bottomInset // Ensures content goes above the keyboard or nav bar
                )
                WindowInsetsCompat.CONSUMED
            }
        }

//        fun setupEdgeToEdge(activity: AppCompatActivity) {
//            val windowInsetsController = ViewCompat.getWindowInsetsController(activity.window.decorView)
//            windowInsetsController?.isAppearanceLightStatusBars = true
//
//            // Handle system bar insets
//            ViewCompat.setOnApplyWindowInsetsListener(activity.findViewById(R.id.main_container)) { view, insets ->
//                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//                view.updatePadding(
//                    top = systemBars.top,
//                    bottom = systemBars.bottom
//                )
//                insets
//            }
//        }

    }

    fun getDateYYMMDDHHMMSS(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }

    fun getUUID():String{
        return UUID.randomUUID().toString()
    }

    private val calendar = Calendar.getInstance()
    fun uuidTimeStamp(): String {
        return SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(Date()) +
                String.format("%04d", Integer.valueOf(Random().nextInt(1001)))
    }

    fun timeStamp(): String {
        return SimpleDateFormat("yyyyMMddss", Locale.getDefault()).format(Date())
    }

    fun getAppVersion(context: Context): String {
        var versionString = ""
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionString = pInfo.versionName.toString()

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionString
    }


    fun isAutoDateTimeEnabled(context: Context): Boolean {
        return try {
            Settings.Global.getInt(context.contentResolver, Settings.Global.AUTO_TIME) == 1
        } catch (e: Settings.SettingNotFoundException) {
            false
        }
    }


    fun monthlySelfDeclaration():ArrayList<monthlySelfDeclation>{
        var arr = ArrayList<monthlySelfDeclation>()
        var list = ArrayList<String>()
        for(i in 0..10){
            list.add(i.toString())
        }

        arr.add(monthlySelfDeclation(noOfField = list,workFieldName = "आपके कार्यक्षेत्र में कुल कितने बदलाव दीदी हैं –",workFieldId = 1))
        arr.add(monthlySelfDeclation(noOfField = list,workFieldName = "आपके कार्यक्षेत्र में कुल कितने बदलाव मंच हैं –",workFieldId = 2))
        arr.add(monthlySelfDeclation(noOfField = list,workFieldName = "आपके कार्यक्षेत्र में कुल कितने बुजुर्ग समूह  हैं –",workFieldId = 3))
        arr.add(monthlySelfDeclation(noOfField = list,workFieldName = "आपके कार्यक्षेत्र में कुल कितने दिब्यांग समूह  हैं – ",workFieldId = 4))
        arr.add(monthlySelfDeclation(noOfField = list,workFieldName = "आपके कार्यक्षेत्र में कुल लकतने VO को VRF राशि मिली  हैं –",workFieldId = 5))

        return arr
    }

    fun dataDeclaration(): ArrayList<modeDeclaration> {
        val arrData = ArrayList<modeDeclaration>()
       // arrData.add(modeDeclaration(activityId= 1,activity = "कार्यक्षेत्र का विवरण", subActviity = "कार्यक्षेत्र का विवरण(जिला/प्रखण्ड/पंचायत)"))
        arrData.add(modeDeclaration(activityId = 2,activity = "स्व घोषणा", subActviity = "कार्य से अनुपस्थित रहने के सम्बन्ध में"))
       // arrData.add(modeDeclaration(activityId = 3,activity = "स्व घोषणा-3(Daily)", subActviity ="DRP एवं SRP के रूप में लिए गए प्रशिक्षण"))
        return arrData
    }

    fun arrAttendance(): ArrayList<AttendanceModel>{
        var arr = ArrayList<AttendanceModel>()
        //arr.add(AttendanceModel(attendanceId = 0,attendanceMark = "Select"))
        arr.add(AttendanceModel(attendanceId = 1,attendanceMark = "No"))
        arr.add(AttendanceModel(attendanceId = 2,attendanceMark = "Yes"))

        return arr
    }
    fun arrTrainingSubject(): ArrayList<TrainingSubjectModel>{
        var arr = ArrayList<TrainingSubjectModel>()
        arr.add(TrainingSubjectModel(trainingId = 0,trainingName = "Select"))
        arr.add(TrainingSubjectModel(trainingId = 1,trainingName = "जेंडर सम्बन्धी प्रशिक्षण "))
        arr.add(TrainingSubjectModel(trainingId = 2,trainingName = "सामाजिक समावेशन सम्बन्धी प्रशिक्षण"))
        arr.add(TrainingSubjectModel(trainingId = 3,trainingName = "कोशोरी एवं बाल संरक्षण"))
        arr.add(TrainingSubjectModel(trainingId = 4,trainingName = "PRI-CBO - प्रशिक्षण"))
        return arr
    }
}


data class monthlySelfDeclation(
    var noOfField: ArrayList<String>? = arrayListOf(),
    var workFieldName: String? = "",
    var workFieldId: Int? = 0
)

data class modeDeclaration(
    val activityId:Int? = 0,
    var activity:String? = "",
    var subActviity:String? = "",
)

data class AttendanceModel(
    var attendanceId :Int? = 0,
    var attendanceMark :String? = "",
){
    override fun toString(): String {
        return attendanceMark.toString()
    }
}

data class TrainingSubjectModel(
    var trainingId :Int? = 0,
    var trainingName :String? = "",
){
    override fun toString(): String {
        return trainingName.toString()
    }
}



