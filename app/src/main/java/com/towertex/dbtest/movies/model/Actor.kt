package com.towertex.dbtest.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "actors",
    indices = [
        Index("id"),
    ],
    primaryKeys = [
        "id"
    ]
)
class Actor (
    @ColumnInfo(name = "born_date")
    val bornDate: Int? = null,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "nick_name")
    val nickName: String? = null
)