package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.Sort
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import kotlinx.coroutines.flow.Flow

interface GetRecipesListUseCase {
    operator fun invoke(sort: Sort): Flow<List<RecipeInfo>>
}