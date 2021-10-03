package com.blogspot.soyamr.domain.recipes

import javax.inject.Inject

interface UpdateRecipesUseCase {
    suspend operator fun invoke()
}

class UpdateRecipesUseCaseImpl @Inject constructor(
    private val recipesDataSource: RecipesDataSource
) : UpdateRecipesUseCase {
    override suspend fun invoke() = recipesDataSource.updateRecipes()
}

