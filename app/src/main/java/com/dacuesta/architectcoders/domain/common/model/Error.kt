package com.dacuesta.architectcoders.domain.common.model

sealed class Error {
    object Empty : Error()
    object Server : Error()
    object Unknown : Error()
}