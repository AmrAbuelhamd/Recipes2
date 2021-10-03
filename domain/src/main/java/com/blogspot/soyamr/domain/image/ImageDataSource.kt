package com.blogspot.soyamr.domain.image

interface ImageDataSource {
    suspend fun downloadImage(url: String): Result<String>
}