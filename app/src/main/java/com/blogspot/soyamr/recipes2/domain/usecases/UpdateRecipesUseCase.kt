package com.blogspot.soyamr.recipes2.domain.usecases

interface UpdateRecipesUseCase {
    suspend operator fun invoke(): Result<Unit>
}