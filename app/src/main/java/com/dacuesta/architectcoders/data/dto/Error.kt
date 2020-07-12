package com.dacuesta.architectcoders.data.dto

sealed class Error {
    object NullBody: Error()
    object Unauthorized : Error()
    object Server : Error()
    object Unknown : Error()
}