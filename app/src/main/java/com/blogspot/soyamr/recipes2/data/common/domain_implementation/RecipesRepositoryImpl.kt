package com.blogspot.soyamr.recipes2.data.common.domain_implementation

import com.blogspot.soyamr.recipes2.data.common.contracts.LocalContract
import com.blogspot.soyamr.recipes2.data.common.contracts.RemoteContract
import com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity.toDomain
import com.blogspot.soyamr.recipes2.data.local.managers.CacheManager
import com.blogspot.soyamr.recipes2.domain.RepositoriesContract
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val localRepository: LocalContract.RecipeRepository,
    private val remoteRepository: RemoteContract.RecipeRepository,
    private val cacheManager: CacheManager,
    private val utilsRepository: LocalContract.UtilsRepository
) : RepositoriesContract.RecipesRepository {
    override suspend fun getRecipes(keyWord: String): Result<List<Recipe>> {
        if (localRepository.getRecipesCount() == 0) {
            updateRecipes()
        }
        return Result.success(localRepository.getRecipes(keyWord).map { it.toDomain() })
    }

    override suspend fun updateRecipes() {
        utilsRepository.clearDatabase()
        remoteRepository.getRecipes().let { recipesList ->
            with(cacheManager) {
                recipesList.map { it.cache() }
            }
        }
    }

}