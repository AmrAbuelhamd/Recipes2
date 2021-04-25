package com.blogspot.soyamr.recipes2.data.local.db.converters

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