package com.dacuesta.architectcoders.domain.mapper

import com.dacuesta.architectcoders.data.model.Error as DataError
import com.dacuesta.architectcoders.domain.model.Error

fun DataError.map() = when (this) {
    is DataError.Empty -> {
        Error.Empty
    }
    is DataError.Server -> {
        Error.Server
    }
    else -> {
        Error.Unknown
    }
}
