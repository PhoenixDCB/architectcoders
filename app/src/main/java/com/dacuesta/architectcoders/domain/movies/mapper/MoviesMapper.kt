package com.dacuesta.architectcoders.domain.movies.mapper

import com.dacuesta.architectcoders.data.movies.model.MoviesMetadata as DataMoviesMetadata
import com.dacuesta.architectcoders.domain.movies.model.MoviesMetadata
import com.dacuesta.architectcoders.data.movies.model.Movie as DataMovie
import com.dacuesta.architectcoders.domain.movies.model.Movie

fun DataMoviesMetadata.map() = MoviesMetadata(
    page = page,
    results = results.map(),
    totalPages = totalPages,
    totalResults = totalResults
)

fun List<DataMovie>.map() = run {
    val movies = mutableListOf<Movie>()
    forEach { movie ->
        movies.add(movie.map())
    }
    movies
}

fun DataMovie.map() = Movie(
    id = id,
    posterPath = posterPath,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    releaseDate = releaseDate
)
