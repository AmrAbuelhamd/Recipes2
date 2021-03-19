package com.blogspot.soyamr.recipes2.domain.interactors

import com.blogspot.soyamr.recipes2.domain.model.Result

interface UpdateRecipesUseCase {
    suspend operator fun invoke(): Result<Unit>
}