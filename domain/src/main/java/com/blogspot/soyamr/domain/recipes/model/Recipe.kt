package com.blogspot.soyamr.domain.recipes.model

import java.util.*


data class Recipe(
    val uuid: String,
    val name: String,
    val image: String,
    val lastUpdated: Date,
    val description: String?
)