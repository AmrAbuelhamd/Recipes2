package com.blogspot.soyamr.recipes2.data.database.converters

import androidx.room.TypeConverter

class IdsConverter {
    @TypeConverter
    fun fromIds(ids: Ids): String = if (ids.ids.isNotEmpty())
        ids.ids.joinToString(separator = ",")
    else
        ""

    @TypeConverter
    fun toIds(ids: String): Ids = if (ids.isNotBlank())
        Ids(ids.split(","))
    else
        Ids(listOf())
}