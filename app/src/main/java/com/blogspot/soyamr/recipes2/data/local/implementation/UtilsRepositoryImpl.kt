package com.blogspot.soyamr.recipes2.data.local.implementation

import com.blogspot.soyamr.recipes2.data.common.contracts.LocalContract
import com.blogspot.soyamr.recipes2.data.local.db.RecipeDataBase
import javax.inject.Inject


class UtilsRepositoryImpl @Inject constructor(
    private val appDatabase: RecipeDataBase
) : LocalContract.UtilsRepository {


    override suspend fun clearDatabase() {
        appDatabase.clearAllTables()
    }
}