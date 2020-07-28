package com.dacuesta.architectcoders.data.repository.mapper

import com.dacuesta.architectcoders.data.dto.moviedetail.MovieDetailDTO
import com.dacuesta.architectcoders.domain.entity.MovieDetailEntity

internal fun map(detail: MovieDetailDTO) =
    MovieDetailEntity(
        title = detail.title,
        overview = detail.overview,
        releaseDate = detail.releaseDate
    )
