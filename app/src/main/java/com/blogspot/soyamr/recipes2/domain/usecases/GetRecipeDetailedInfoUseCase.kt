package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo

interface GetRecipeDetailedInfoUseCase {
    suspend operator fun invoke(id: String): Result<RecipeDetailedInfo>
}