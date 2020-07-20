package com.dacuesta.architectcoders.domain.common.extension

import com.dacuesta.architectcoders.data.common.model.movies.MoviesMetadata as DataMoviesMetadata
import com.dacuesta.architectcoders.domain.common.model.movies.MoviesMetadata
import com.dacuesta.architectcoders.data.common.model.movies.Movie as DataMovie
import com.dacuesta.architectcoders.domain.common.model.movies.Movie

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
    imageUrl = "https://image.tmdb.org/t/p/w185${posterPath}",
    title = originalTitle,
    releaseDate = releaseDate
)
