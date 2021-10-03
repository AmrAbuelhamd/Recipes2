package com.blogspot.soyamr.domain.image

import javax.inject.Inject

interface DownloadImageUseCase {
    suspend operator fun invoke(url: String): Result<String>
}

class DownloadImageUseCaseImpl @Inject constructor(
    private val downloadImageUseCase: DownloadImageUseCase
) : DownloadImageUseCase {
    override suspend fun invoke(url: String): Result<String> =
        downloadImageUseCase.invoke(url)
}

