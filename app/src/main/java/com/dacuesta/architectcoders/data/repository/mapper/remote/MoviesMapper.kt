package com.dacuesta.architectcoders.data.repository.mapper.remote

import com.dacuesta.architectcoders.data.remote.dto.movies.MoviesMetadataDTO
import com.dacuesta.architectcoders.domain.entity.movies.MoviesMetadataEntity
import com.dacuesta.architectcoders.data.remote.dto.movies.MovieDTO
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity

internal fun map(metadata: MoviesMetadataDTO) = MoviesMetadataEntity(
    page = metadata.page,
    results = map(
        metadata.results
    ),
    totalPages = metadata.totalPages,
    totalResults = metadata.totalResults
)

private fun map(movies: List<MovieDTO>) = run {
    val list = mutableListOf<MovieEntity>()
    movies.forEach { movie ->
        list.add(
            map(
                movie
            )
        )
    }
    list
}

private fun map(movie: MovieDTO) = MovieEntity(
    id = movie.id,
    imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}",
    title = movie.originalTitle,
    releaseDate = movie.releaseDate
)
