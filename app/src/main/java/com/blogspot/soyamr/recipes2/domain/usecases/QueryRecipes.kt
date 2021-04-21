package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import kotlinx.coroutines.flow.Flow


interface QueryRecipes {
    operator fun invoke(keyWord: String): Flow<List<RecipeInfo>>
}