package com.dacuesta.architectcoders.domain.entity

sealed class ErrorEntity {
    object Server : ErrorEntity()
    object Unknown : ErrorEntity()
}