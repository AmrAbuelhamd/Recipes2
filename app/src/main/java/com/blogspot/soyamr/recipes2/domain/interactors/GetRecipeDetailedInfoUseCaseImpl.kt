package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.Repository
import javax.inject.Inject

class GetRecipeDetailedInfoUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetRecipeDetailedInfoUseCase {
    override suspend fun invoke(id: String) = repository.getRecipeDetails(id)
}