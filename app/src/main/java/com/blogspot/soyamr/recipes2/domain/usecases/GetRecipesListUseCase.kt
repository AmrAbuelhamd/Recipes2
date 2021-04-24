package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe

interface GetRecipesListUseCase {
    suspend operator fun invoke(keyWord: String): Result<List<Recipe>>
}