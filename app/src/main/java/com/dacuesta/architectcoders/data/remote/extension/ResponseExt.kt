package com.dacuesta.architectcoders.data.remote.extension

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.remote.dto.ErrorDTO
import retrofit2.Response

fun <T> Response<T>.result(): Either<ErrorDTO, T> = try {
    if (isSuccessful) {
        body()?.right() ?: ErrorDTO.Empty.left()
    } else {
        when (code()) {
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