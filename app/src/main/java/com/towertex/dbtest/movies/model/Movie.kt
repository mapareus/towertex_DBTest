package com.towertex.dbtest.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "movies",
    indices = [
        Index("id"),
    ],
    primaryKeys = [
        "id"
    ]
)
data class Movie(
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: Int? = null,
    @ColumnInfo(name = "genre_names")
    val genreNames: List<String>? = null,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "actor_ids")
    val actorIds: List<Int>? = null
)