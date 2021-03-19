package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.domain.model.Result

interface GetRecipeDetailedInfoUseCase {
    suspend operator fun invoke(id: String): Result<RecipeDetailedInfo>
}