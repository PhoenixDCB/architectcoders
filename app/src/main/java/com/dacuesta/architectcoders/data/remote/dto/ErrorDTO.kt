package com.dacuesta.architectcoders.data.remote.dto

sealed class ErrorDTO {
    object Empty: ErrorDTO()
    object Unauthorized : ErrorDTO()
    object Server : ErrorDTO()
    object Unknown : ErrorDTO()
}