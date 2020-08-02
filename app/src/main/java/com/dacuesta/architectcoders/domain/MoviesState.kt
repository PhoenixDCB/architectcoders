package com.dacuesta.architectcoders.domain

import com.dacuesta.architectcoders.domain.entity.ErrorEntity

sealed class MoviesState<T> {
    class Loading<T>: MoviesState<T>()
    data class Success<T>(val value: T): MoviesState<T>()
    data class Failure<T>(val error: ErrorEntity): MoviesState<T>()
}