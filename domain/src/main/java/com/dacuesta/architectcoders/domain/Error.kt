package com.dacuesta.architectcoders.domain

sealed class Error {
    object Empty: Error()
    object Unauthorized : Error()
    object Server : Error()
    object Unknown : Error()
}