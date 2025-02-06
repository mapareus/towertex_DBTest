package com.towertex.dbtest.students.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "SHOLARSHIPS",
    indices = [
        Index("STUDENT_REF_ID"),
    ],
    primaryKeys = [
        "STUDENT_REF_ID"
    ]
)
class Scholarship (
    @ColumnInfo(name = "STUDENT_REF_ID")
    val studentRefId: Int,
    @ColumnInfo(name = "SCHOLARSHIP_AMOUNT")
    val scholarshipAmount: Int,
    @ColumnInfo(name = "SCHOLARSHIP_DATE")
    val scholarshipDate: String,
)