package com.blogspot.soyamr.recipes2.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.blogspot.soyamr.recipes2.data.local.db.converters.Photos
import com.blogspot.soyamr.recipes2.data.local.db.converters.PhotosConverter


@Entity
@TypeConverters(PhotosConverter::class)
data class RecipeEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    val images: Photos,
    val lastUpdated: Int,
    val description: String?,
    val instructions: String,
    val difficulty: Int
)