package com.dacuesta.architectcoders.domain.common.extension

import com.dacuesta.architectcoders.data.common.model.Error as DataError
import com.dacuesta.architectcoders.domain.common.model.Error

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
