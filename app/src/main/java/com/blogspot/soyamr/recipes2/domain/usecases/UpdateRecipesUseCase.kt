package com.blogspot.soyamr.recipes2.domain.usecases

import com.blogspot.soyamr.recipes2.domain.entities.Result

interface UpdateRecipesUseCase {
    suspend operator fun invoke(): Result<Unit>
}