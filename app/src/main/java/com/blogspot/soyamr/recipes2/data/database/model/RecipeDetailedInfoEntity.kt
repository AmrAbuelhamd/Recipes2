package com.blogspot.soyamr.recipes2.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.blogspot.soyamr.recipes2.data.database.converters.Ids
import com.blogspot.soyamr.recipes2.data.database.converters.IdsConverter
import com.blogspot.soyamr.recipes2.data.database.converters.Photos
import com.blogspot.soyamr.recipes2.data.database.converters.PhotosConverter


@Entity
@TypeConverters(PhotosConverter::class, IdsConverter::class)
data class RecipeDetailedInfoEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    val images: Photos,
    val lastUpdated: Int,
    val description: String?,
    val instructions: String,
    val difficulty: Int,
    val similar: Ids
)