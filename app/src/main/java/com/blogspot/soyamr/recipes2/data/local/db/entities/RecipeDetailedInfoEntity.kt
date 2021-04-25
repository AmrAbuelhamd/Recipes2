package com.blogspot.soyamr.recipes2.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.blogspot.soyamr.recipes2.data.local.db.converters.Ids
import com.blogspot.soyamr.recipes2.data.local.db.converters.IdsConverter
import com.blogspot.soyamr.recipes2.data.local.db.converters.Photos
import com.blogspot.soyamr.recipes2.data.local.db.converters.PhotosConverter


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