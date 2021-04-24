package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.RecipeRepository
import javax.inject.Inject

class UpdateRecipesUseCaseImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    UpdateRecipesUseCase {
    override suspend fun invoke(): Result<Unit> = recipeRepository.updateRecipes()
}