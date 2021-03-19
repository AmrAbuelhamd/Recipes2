package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.Repository
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryRecipesImpl @Inject constructor(val repository: Repository) : QueryRecipes {
    override fun invoke(keyWord: String): Flow<List<RecipeInfo>> = repository.queryRecipes(keyWord)
}