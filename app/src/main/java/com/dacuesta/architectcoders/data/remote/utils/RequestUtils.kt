package com.dacuesta.architectcoders.data.remote.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.remote.dto.ErrorDTO
import retrofit2.Response
import java.lang.Exception

suspend fun <T> invoke(request: suspend () -> Response<T>): Either<ErrorDTO, T> = try {
    val response = request()
    if (response.isSuccessful) {
        response.body()?.right() ?: ErrorDTO.Empty.left()
    } else {
        when (response.code()) {
            401 -> {
                ErrorDTO.Unauthorized.left()
            }
            422 -> {
                ErrorDTO.Empty.left()
            }
            500 -> {
                ErrorDTO.Server.left()
            }
            else -> {
                ErrorDTO.Unknown.left()
            }
        }
    }
} catch (e: Exception) {
    ErrorDTO.Unknown.left()
}