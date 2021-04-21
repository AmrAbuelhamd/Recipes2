package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.Repository
import com.blogspot.soyamr.recipes2.domain.Sort
import javax.inject.Inject

class GetRecipesListUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetRecipesListUseCase {
    override operator fun invoke(sort: Sort) = repository.getRecipes(sort)
}