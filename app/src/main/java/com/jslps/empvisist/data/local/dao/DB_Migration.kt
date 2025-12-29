package com.jslps.empvisist.data.local.dao

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {

        //SCHEMA FOR CLUSTER TABLE
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS tblClusterList (
                BlockCode TEXT,
                ClusterCode TEXT NOT NULL,
                ClusterName TEXT,
                ClusterName_H TEXT,
                PRIMARY KEY(ClusterCode)
            )
        """.trimIndent())
        // Add index on ClusterCode
        database.execSQL("""
            CREATE UNIQUE INDEX IF NOT EXISTS index_tblClusterList_ClusterCode
            ON tblClusterList (ClusterCode)
        """.trimIndent())


        //SCHEMA FOR VILLAGE TABLE
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS tblVillageList (
                BlockCode TEXT,
                ClusterCode TEXT,
                PanchayatName TEXT,
                VillageCode TEXT NOT NULL,
                VillageName TEXT,
                VillageName_H TEXT,
                PRIMARY KEY(VillageCode)
            )
        """.trimIndent())
        // Add unique index on VillageCode
        database.execSQL("""
            CREATE UNIQUE INDEX IF NOT EXISTS index_tblVillageList_VillageCode
            ON tblVillageList (VillageCode)
        """.trimIndent())


        //SCHEMA FOR VILLAGE SELFDECLMONTHLY
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS tblSelfDeclMonthly (
                selfDeclId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId INTEGER,
                username TEXT,
                districtcode TEXT,
                bloackcode TEXT,
                clustercode TEXT,
                villagecode TEXT,
                month TEXT,
                year TEXT,
                uuid TEXT,
                noOfField TEXT,
                workFieldName TEXT,
                createdDate TEXT,
                workFieldId INTEGER,
                isExported INTEGER,
                UNIQUE(userId, month, year, workFieldId)
            )
            """.trimIndent()
        )



        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS tblDailyAttendance (
                userId INTEGER,
                username TEXT,
                districtcode TEXT,
                bloackcode TEXT,
                clustercode TEXT,
                villagecode TEXT,
                month TEXT,
                year TEXT,
                date TEXT,
                markAttendanceId INTEGER,
                markAttendance TEXT,
                attendanceDate TEXT,
                isDrpSrpTraining TEXT,
                isDrpSrpTrainingId INTEGER,
                trainingSubject TEXT,
                trainingSubjectId INTEGER,
                trainingFromDate TEXT,
                trainingToDate TEXT,
                createdDate TEXT,
                uuid TEXT,
                isExported INTEGER,
                selfAttenId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                UNIQUE(userId, attendanceDate)
            )
            """.trimIndent()
        )


        //SCHEMA FOR WORK LIMIT TABLE
        // 1. Create new table with the updated schema
        database.execSQL(
            """
            CREATE TABLE tblWorkLimit_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                workLimit INTEGER NOT NULL,
                workDate TEXT NOT NULL,
                userId TEXT NOT NULL,
                subCatId INTEGER DEFAULT 0,
                UNIQUE(userId, workDate, subCatId)
            )
            """.trimIndent()
        )

        // 2. Copy data from old table to new table (subCatId defaults to 0)
        database.execSQL(
            """
            INSERT INTO tblWorkLimit_new (id, workLimit, workDate, userId, subCatId)
            SELECT id, workLimit, workDate, userId, 0 FROM tblWorkLimit
            """.trimIndent()
        )

        // 3. Remove the old table
        database.execSQL("DROP TABLE tblWorkLimit")

        // 4. Rename new table to old table name
        database.execSQL("ALTER TABLE tblWorkLimit_new RENAME TO tblWorkLimit")


        // ADD COLUMN IN ANSWER TABLE
        database.execSQL("ALTER TABLE tblAnswer ADD COLUMN districtcode TEXT DEFAULT ''")
        database.execSQL("ALTER TABLE tblAnswer ADD COLUMN bloackcode TEXT DEFAULT ''")
        database.execSQL("ALTER TABLE tblAnswer ADD COLUMN clustercode TEXT DEFAULT ''")
        database.execSQL("ALTER TABLE tblAnswer ADD COLUMN villagecode TEXT DEFAULT ''")
        database.execSQL("ALTER TABLE tblAnswer ADD COLUMN uuidParent TEXT DEFAULT ''")




        database.execSQL("DROP TABLE IF EXISTS tblmstCategoryBRPCP")
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS tblmstCategoryBRPCP (
                primaryKeyCatlId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                Cat_ControlID INTEGER,
                Cat_ID INTEGER,
                Cat_Name TEXT,
                Cat_Status INTEGER,
                Monthly INTEGER,
                Daily INTEGER,
                Cat_ImageBase64 TEXT,
                WorkLimit TEXT
            )
        """.trimIndent())
        database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_tblmstCategoryBRPCP_Cat_ID ON tblmstCategoryBRPCP (Cat_ID)")
    }


}


val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE tblBVJVYGrpMeetParent ADD COLUMN PurposeId TEXT DEFAULT ''")
    }
}

val MIGRATION_22_33 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the table
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `tblImageDocument` (
                `DocID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `imageName` TEXT,
                `imageURL` TEXT,
                `base64` TEXT,
                `uuidParent` TEXT,
                `CreatedDate` TEXT,
                `created_by` TEXT,
                `appVersion` TEXT,
                UNIQUE (`uuidParent`)
            )
        """.trimIndent()
        )

        // Create index on uuidParent
        database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_tblImageDocument_uuidParent` ON `tblImageDocument` (`uuidParent`)")
    }
}


