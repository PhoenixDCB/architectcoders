package com.dacuesta.architectcoders.presentation.utils

import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.domain.entity.ErrorEntity

fun errorMessage(error: ErrorEntity) =
    when (error) {
        is ErrorEntity.Server -> {
            R.string.error_server
        }
        else -> {
            R.string.error_unknown
        }
    }