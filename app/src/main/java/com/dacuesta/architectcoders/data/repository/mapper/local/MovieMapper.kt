package com.dacuesta.architectcoders.data.repository.mapper.local

import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.data.local.dto.MovieDTO


internal fun map(movie: MovieEntity) = MovieDTO(
    id = movie.id,
    imageUrl = movie.imageUrl,
    title = movie.title,
    releaseDate = movie.releaseDate
)

internal fun map(movie: MovieDTO) = MovieEntity(
    id = movie.id,
    imageUrl = movie.imageUrl,
    title = movie.title,
    releaseDate = movie.releaseDate
)
