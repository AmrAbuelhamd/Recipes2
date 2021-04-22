package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.RecipeRepository
import javax.inject.Inject

class GetRecipeDetailedInfoUseCaseImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    GetRecipeDetailedInfoUseCase {
    override suspend fun invoke(id: String) = recipeRepository.getRecipeDetails(id)
}