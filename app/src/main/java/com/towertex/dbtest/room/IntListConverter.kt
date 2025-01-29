package com.towertex.dbtest.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class IntListConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return Json.encodeToString(list)
    }
}