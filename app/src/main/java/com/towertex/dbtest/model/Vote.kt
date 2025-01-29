package com.towertex.dbtest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "votes",
    indices = [
        Index("id"),
    ],
    primaryKeys = [
        "id"
    ]
)
data class Vote(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "vote")
    val vote: Float,
)