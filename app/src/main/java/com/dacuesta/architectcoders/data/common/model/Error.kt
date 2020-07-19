package com.dacuesta.architectcoders.data.common.model

sealed class Error {
    object Empty: Error()
    object Unauthorized : Error()
    object Server : Error()
    object Unknown : Error()
}