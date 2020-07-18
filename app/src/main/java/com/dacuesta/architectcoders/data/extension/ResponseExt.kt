package com.dacuesta.architectcoders.data.extension

import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.model.Error
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> Response<T>.result() = flow {
    if (isSuccessful) {
        body()?.let { body ->
            emit(body.right())
        } ?: emit(Error.Empty.left())
    } else {
        when (code()) {
            401 -> {
                emit(Error.Unauthorized.left())
            }
            422 -> {
                emit(Error.Empty.left())
            }
            500 -> {
                emit(Error.Server.left())
            }
            else -> {
                emit(Error.Unknown.left())
            }
        }
    }
}.catch {
    emit(Error.Unknown.left())
}