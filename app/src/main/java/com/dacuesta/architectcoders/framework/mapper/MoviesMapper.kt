package com.dacuesta.architectcoders.framework.mapper

import com.dacuesta.architectcoders.framework.room.model.Movie as RoomMovie
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata as DomainMoviesMetadata
import com.dacuesta.architectcoders.framework.tmdb.model.movies.MoviesMetadata as TmdbMoviesMetadata
import com.dacuesta.architectcoders.domain.movies.Movie as DomainMovie
import com.dacuesta.architectcoders.framework.tmdb.model.movies.Movie as TmdbMovie

internal fun map(metadata: TmdbMoviesMetadata) = DomainMoviesMetadata(
    page = metadata.page,
    results = map(metadata.results),
    totalPages = metadata.totalPages,
    totalResults = metadata.totalResults
)

private fun map(movies: List<TmdbMovie>) = run {
    val list = mutableListOf<DomainMovie>()
    movies.forEach { movie ->
        list.add(map(movie))
    }
    list
}

private fun map(movie: TmdbMovie) = DomainMovie(
    id = movie.id,
    posterImageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}",
    backdropImageUrl = "https://image.tmdb.org/t/p/w185${movie.backdropPath}",
    title = movie.originalTitle,
    releaseDate = movie.releaseDate
)


internal fun map(movie: DomainMovie) = RoomMovie(
    id = movie.id,
    posterImageUrl = movie.posterImageUrl,
    backdropImageUrl = movie.backdropImageUrl,
    title = movie.title,
    releaseDate = movie.releaseDate
)


internal fun map(movie: RoomMovie) = DomainMovie(
    id = movie.id,
    posterImageUrl = movie.posterImageUrl,
    backdropImageUrl = movie.backdropImageUrl,
    title = movie.title,
    releaseDate = movie.releaseDate
)
