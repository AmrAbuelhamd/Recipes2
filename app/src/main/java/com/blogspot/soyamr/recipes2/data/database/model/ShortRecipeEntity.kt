package com.blogspot.soyamr.recipes2.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ShortRecipeEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    val images: String,
)