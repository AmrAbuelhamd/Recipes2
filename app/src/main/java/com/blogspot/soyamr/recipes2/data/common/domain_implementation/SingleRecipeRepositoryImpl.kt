package com.blogspot.soyamr.recipes2.data.common.domain_implementation

import com.blogspot.soyamr.recipes2.data.common.contracts.LocalContract
import com.blogspot.soyamr.recipes2.data.common.contracts.RemoteContract
import com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity.LocalEntityToDomainEntityMapper
import com.blogspot.soyamr.recipes2.data.local.managers.CacheManager
import com.blogspot.soyamr.recipes2.domain.RepositoriesContract
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import javax.inject.Inject

class SingleRecipeRepositoryImpl @Inject constructor(
    private val localRepository: LocalContract.RecipeRepository,
    private val remoteRepository: RemoteContract.RecipeRepository,
    private val localEntityToDomainEntityMapper: LocalEntityToDomainEntityMapper,
    private val cacheManager: CacheManager
) : RepositoriesContract.SingleRecipeRepository {

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo> =
        with(localEntityToDomainEntityMapper) {
            if (localRepository.getRecipeDetails(id) == null) {
                remoteRepository.getRecipeDetailedInfo(id).let {
                    with(cacheManager) {
                        it.cache()
                    }
                }
            }
            Result.success(localRepository.getRecipeDetails(id)!!.toDomain())
        }
}