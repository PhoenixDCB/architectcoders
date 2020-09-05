package com.dacuesta.architectcoders.framework.mapper

import com.dacuesta.architectcoders.domain.Movie as DomainMovie
import com.dacuesta.architectcoders.framework.room.model.PopularMovie as RoomPopularMovie
import com.dacuesta.architectcoders.framework.room.model.FavoriteMovie as RoomFavoriteMovie
import com.dacuesta.architectcoders.framework.tmdb.model.movies.Movie as TmdbMovie
import com.dacuesta.architectcoders.framework.tmdb.model.movies.MoviesMetadata as TmdbMoviesMetadata

internal fun map(metadata: TmdbMoviesMetadata): List<DomainMovie> =
    map(metadata.results)

private fun map(movies: List<TmdbMovie>): List<DomainMovie> =
    run {
        val list = mutableListOf<DomainMovie>()
        movies.forEach { movie ->
            list.add(map(movie))
        }
        list
    }

private fun map(movie: TmdbMovie) =
    DomainMovie(
        id = movie.id,
        posterImageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}",
        backdropImageUrl = "https://image.tmdb.org/t/p/w185${movie.backdropPath}",
        title = movie.originalTitle,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity
    )

internal fun mapToRoomPopular(movie: DomainMovie) =
    RoomPopularMovie(
        id = movie.id,
        posterImageUrl = movie.posterImageUrl,
        backdropImageUrl = movie.backdropImageUrl,
        title = movie.title,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity
    )


internal fun map(movie: RoomPopularMovie) =
    DomainMovie(
        id = movie.id,
        posterImageUrl = movie.posterImageUrl,
        backdropImageUrl = movie.backdropImageUrl,
        title = movie.title,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity
    )


internal fun mapToRoomFavorite(movie: DomainMovie) =
    RoomFavoriteMovie(
        id = movie.id,
        posterImageUrl = movie.posterImageUrl,
        backdropImageUrl = movie.backdropImageUrl,
        title = movie.title,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity
    )


internal fun map(movie: RoomFavoriteMovie) =
    DomainMovie(
        id = movie.id,
        posterImageUrl = movie.posterImageUrl,
        backdropImageUrl = movie.backdropImageUrl,
        title = movie.title,
        releaseDate = movie.releaseDate,
        popularity = movie.popularity
    )
