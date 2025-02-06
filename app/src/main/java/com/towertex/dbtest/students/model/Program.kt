package com.towertex.dbtest.students.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "PROGRAMS",
    indices = [
        Index("STUDENT_REF_ID"),
    ],
    primaryKeys = [
        "STUDENT_REF_ID"
    ]
)
class Program (
    @ColumnInfo(name = "STUDENT_REF_ID")
    val studentRefId: Int,
    @ColumnInfo(name = "PROGRAM_NAME")
    val programName: String,
    @ColumnInfo(name = "PROGRAM_START_DATE")
    val programStartDate: String,
)