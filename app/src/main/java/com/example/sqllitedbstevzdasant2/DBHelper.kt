package com.example.sqllitedbstevzdasant2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    factory: SQLiteDatabase.CursorFactory?,
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val queryStudent = ("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_TICKET + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SECOND_NAME + " TEXT, " +
                COLUMN_PATRONYMIC + " TEXT, " +
                COLUMN_GROUP + " TEXT" + " )")

        val queryGroup = ("CREATE TABLE " + gTABLE_NAME + " (" +
                gCOLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                gCOLUMN_GROUP_NUMBER + " TEXT, " +
                gCOLUMN_NUMBER_OF_STUDENTS + " INTEGER, " +
                gCOLUMN_FACULTY + " TEXT, " +
                gCOLUMN_TRAINING_PROFILE + " TEXT, " +
                gCOLUMN_GROUP_LEADER + " TEXT" + " )")

        db?.execSQL(queryStudent)
        db?.execSQL(queryGroup)
    }

    fun getAllStudents(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun deleteStudentsData(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    fun deleteGroupData(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $gTABLE_NAME")
    }


    fun addStudent(
        ticket: String, name: String, secondName:
        String, patronymic: String, studentGroup: String
    ) {

        val values = ContentValues()
        values.apply {
            values.put(COLUMN_NAME, name)
            values.put(COLUMN_SECOND_NAME, secondName)
            values.put(COLUMN_PATRONYMIC, patronymic)
            values.put(COLUMN_GROUP, studentGroup)
            values.put(COLUMN_TICKET, ticket)
        }

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun addStudentGroup(
        id: Int, groupNumber: String, numberOfStudent: Int,
        faculty: String, trainingProfile: String, groupLeader: String
    ) {
        val values = ContentValues()
        values.apply {
            values.put(gCOLUMN_ID, id)
            values.put(gCOLUMN_FACULTY, faculty)
            values.put(gCOLUMN_GROUP_LEADER, groupLeader)
            values.put(gCOLUMN_NUMBER_OF_STUDENTS, numberOfStudent)
            values.put(gCOLUMN_TRAINING_PROFILE, trainingProfile)
            values.put(gCOLUMN_GROUP_NUMBER, groupNumber)
        }

        val db = this.writableDatabase
        db.insert(gTABLE_NAME, null, values)

        db.close()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val context: Context? = null
        private const val DATABASE_NAME = "StudentsDatabase.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "students_table"
        const val COLUMN_TICKET = "ticket"
        const val COLUMN_NAME = "name"
        const val COLUMN_SECOND_NAME = "second_name"
        const val COLUMN_PATRONYMIC = "patronymic"
        const val COLUMN_GROUP = "student_group"

        const val gTABLE_NAME = "groups_table"
        const val gCOLUMN_ID = "id"
        const val gCOLUMN_GROUP_NUMBER = "group_number"
        const val gCOLUMN_NUMBER_OF_STUDENTS = "number_of_students"
        const val gCOLUMN_FACULTY = "faculty"
        const val gCOLUMN_TRAINING_PROFILE = "training_profile"
        const val gCOLUMN_GROUP_LEADER = "group_leader"
    }
}