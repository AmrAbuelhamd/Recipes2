package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.Repository
import javax.inject.Inject

class GetRecipesListUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetRecipesListUseCase {
    override operator fun invoke() = repository.getRecipes()
}