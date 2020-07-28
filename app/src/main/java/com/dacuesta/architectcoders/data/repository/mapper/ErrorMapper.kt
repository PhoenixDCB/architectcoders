package com.dacuesta.architectcoders.data.repository.mapper

import com.dacuesta.architectcoders.data.dto.ErrorDTO
import com.dacuesta.architectcoders.domain.entity.ErrorEntity

internal fun map(error: ErrorDTO) = when (error) {
    is ErrorDTO.Empty -> {
        ErrorEntity.Empty
    }
    is ErrorDTO.Server -> {
        ErrorEntity.Server
    }
    else -> {
        ErrorEntity.Unknown
    }
}
