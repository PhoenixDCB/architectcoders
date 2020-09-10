package com.dacuesta.architectcoders.framework.source.movies

import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.mapper.mapToRoomFavorite
import com.dacuesta.architectcoders.framework.mapper.mapToRoomPopular
import com.dacuesta.architectcoders.framework.room.dao.FavoriteMovieDAO
import com.dacuesta.architectcoders.framework.room.dao.PopularMovieDAO
import com.dacuesta.architectcoders.framework.room.model.PopularMovie
import com.dacuesta.architectcoders.domain.Movie as DomainMovie

internal class MoviesLocalDataSourceImpl(
    private val popularMovieDAO: PopularMovieDAO,
    private val favoriteMovieDAO: FavoriteMovieDAO
) : MoviesLocalDataSource {

    override fun insertPopularMovies(movies: List<DomainMovie>) {
        val roomMovies = mutableListOf<PopularMovie>()
        movies.forEach { domainMovie ->
            roomMovies.add(mapToRoomPopular(domainMovie))
        }
        popularMovieDAO.insert(*roomMovies.toTypedArray())
    }

    override fun deleteAllPopularMovies() {
        popularMovieDAO.deleteAll()
    }

    override fun getAllPopularMovies(): List<DomainMovie> =
        popularMovieDAO.getAll().map(::map)

    override fun insertFavoriteMovie(movie: DomainMovie) {
        val roomMovie = mapToRoomFavorite(movie)
        favoriteMovieDAO.insert(roomMovie)
    }

    override fun deleteFavoriteMovie(movie: DomainMovie) {
        val roomMovie = mapToRoomFavorite(movie)
        favoriteMovieDAO.delete(roomMovie)
    }

    override fun getAllFavoriteMovies(): List<DomainMovie> =
        favoriteMovieDAO.getAll().map(::map)
}