package com.dacuesta.architectcoders.presentation.utils

import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Error.*

fun Error.toMessage(): Int =
    when (this) {
        is Empty, Unauthorized, Server -> {
            R.string.error_server
        }
        is Unknown -> {
            R.string.error_unknown
        }
    }