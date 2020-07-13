package com.dacuesta.architectcoders.domain.model

sealed class Error {
    object Empty : Error()
    object Server : Error()
    object Unknown : Error()
}