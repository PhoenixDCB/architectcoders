package com.dacuesta.architectcoders.framework.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.domain.Error
import retrofit2.Response

internal suspend fun <T> invoke(request: suspend () -> Response<T>): Either<Error, T> = try {
    val response = request()
    if (response.isSuccessful) {
        response.body()?.right() ?: Error.Empty.left()
    } else {
        when (response.code()) {
            401 -> {
                Error.Unauthorized.left()
            }
            422 -> {
                Error.Empty.left()
            }
            500 -> {
                Error.Server.left()
            }
            else -> {
                Error.Unknown.left()
            }
        }
    }
} catch (e: Exception) {
    Error.Unknown.left()
}