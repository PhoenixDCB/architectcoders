package com.dacuesta.architectcoders.data.remote.extension

import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.dto.ErrorDTO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> Response<T>.result() = flow {
    if (isSuccessful) {
        body()?.let { body ->
            emit(body.right())
        } ?: emit(ErrorDTO.Empty.left())
    } else {
        when (code()) {
            401 -> {
                emit(ErrorDTO.Unauthorized.left())
            }
            422 -> {
                emit(ErrorDTO.Empty.left())
            }
            500 -> {
                emit(ErrorDTO.Server.left())
            }
            else -> {
                emit(ErrorDTO.Unknown.left())
            }
        }
    }
}.catch {
    emit(ErrorDTO.Unknown.left())
}