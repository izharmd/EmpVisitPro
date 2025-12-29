package com.jslps.empvisist.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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


@Database(
    entities = [tblmstCategoryBRPCP::class, tblmstControlBRPCP::class, tblmstQuestionBRPCP::class,
        tblmstSubCatBRPCP::class, tblmstSubCatCBRPCP::class, tblAnswer::class, tblmstOptionBRPCP::class,
       tblWorkLimit::class, tblHalfDay::class, tblLogin::class, tblSelfDeclMonthly::class,
        tblDailyAttendance::class, tblClusterList::class, tblVillageList::class],
    version = 2,
    exportSchema = true

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): com.jslps.empvisist.data.local.dao.AppDao
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "BRPDATABASE"
        ).addMigrations(MIGRATION_1_2).build()
    }
}
