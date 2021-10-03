package com.blogspot.soyamr.domain.recipeDetails

import com.blogspot.soyamr.domain.recipeDetails.model.RecipeDetailedInfo
import javax.inject.Inject

interface GetRecipeDetailsUseCase {
    suspend operator fun invoke(id: String): Result<RecipeDetailedInfo>
}

class GetRecipeDetailsUseCaseImpl @Inject constructor(
    private val recipeDetailsDataSource: RecipeDetailsDataSource
) : GetRecipeDetailsUseCase {
    override suspend fun invoke(id: String): Result<RecipeDetailedInfo> =
        recipeDetailsDataSource.getRecipeDetails(id)
}

