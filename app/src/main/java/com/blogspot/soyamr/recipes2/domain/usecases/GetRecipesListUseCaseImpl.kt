package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.RecipeRepository
import javax.inject.Inject

class GetRecipesListUseCaseImpl @Inject constructor(private val recipeRepository: RecipeRepository) :
    GetRecipesListUseCase {
    override suspend operator fun invoke(keyWord: String) =
        recipeRepository.getRecipes(keyWord)
}