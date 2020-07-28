package com.dacuesta.architectcoders.domain.entity

sealed class ErrorEntity {
    object Empty : ErrorEntity()
    object Server : ErrorEntity()
    object Unknown : ErrorEntity()
}