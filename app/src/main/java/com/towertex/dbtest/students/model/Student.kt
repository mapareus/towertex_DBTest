package com.towertex.dbtest.students.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "STUDENTS",
    indices = [
        Index("STUDENT_ID"),
    ],
    primaryKeys = [
        "STUDENT_ID"
    ]
)
class Student (
    @ColumnInfo(name = "STUDENT_ID")
    val studentId: Int,
    @ColumnInfo(name = "FIRST_NAME")
    val firstName: String,
    @ColumnInfo(name = "LAST_NAME")
    val lastName: String,
    @ColumnInfo(name = "GPA")
    val gpa: Float,
    @ColumnInfo(name = "ENROLLMENT_DATE")
    val enrollmentDate: String,
    @ColumnInfo(name = "MAJOR")
    val major: String
)