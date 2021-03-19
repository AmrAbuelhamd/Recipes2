package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.Repository
import javax.inject.Inject

class UpdateRecipesUseCaseImpl @Inject constructor(private val repository: Repository) :
    UpdateRecipesUseCase {
    override suspend fun invoke() = repository.updateRecipes()
}