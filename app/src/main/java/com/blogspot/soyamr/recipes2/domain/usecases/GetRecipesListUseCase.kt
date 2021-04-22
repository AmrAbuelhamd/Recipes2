package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.entities.Result
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipesListUseCase {
    suspend operator fun invoke(keyWord: String): Result<List<Recipe>>
}