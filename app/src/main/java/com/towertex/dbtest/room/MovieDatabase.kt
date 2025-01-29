package com.towertex.dbtest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.towertex.dbtest.model.Actor
import com.towertex.dbtest.model.Movie
import com.towertex.dbtest.model.Vote

@Database(
    entities = [
        Actor::class,
        Movie::class,
        Vote::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class, IntListConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao

    companion object {
        private const val DATABASE_NAME = "MyDatabase"

        fun buildDatabase(context: Context): MovieDatabase = Room
            .databaseBuilder(context, MovieDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(StringListConverter())
            .addTypeConverter(IntListConverter())
            .build()
    }
}