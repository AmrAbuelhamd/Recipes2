package com.blogspot.soyamr.domain.recipes

import com.blogspot.soyamr.domain.recipes.model.Recipe
import javax.inject.Inject

interface GetRecipesUseCase {
    suspend operator fun invoke(keyword: String): Result<List<Recipe>>
}

class GetRecipesUseCaseImpl @Inject constructor(
    private val recipesDataSource: RecipesDataSource
) : GetRecipesUseCase {
    override suspend fun invoke(keyword: String): Result<List<Recipe>> =
        recipesDataSource.getRecipes(keyword)
}

