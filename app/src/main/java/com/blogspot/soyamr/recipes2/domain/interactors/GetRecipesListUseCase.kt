package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import kotlinx.coroutines.flow.Flow

interface GetRecipesListUseCase {
    operator fun invoke(): Flow<List<RecipeInfo>>
}