package com.blogspot.soyamr.recipes2.data.database.converters

import androidx.room.TypeConverter

class PhotosConverter {
    @TypeConverter
    fun fromPhotos(photos: Photos): String = if (photos.urls.isNotEmpty())
        photos.urls.joinToString(separator = " ")
    else
        ""

    @TypeConverter
    fun toPhotos(photos: String): Photos = if (photos.isNotBlank())
        Photos(photos.split(" "))
    else
        Photos(listOf())
}