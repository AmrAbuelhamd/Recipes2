package com.blogspot.soyamr.recipes2.domain.entities

sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
  if (this is Success) action(data)
  return this
}