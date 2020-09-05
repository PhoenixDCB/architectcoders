package com.dacuesta.architectcoders.framework.source.movies

import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.mapper.mapToRoomFavorite
import com.dacuesta.architectcoders.framework.mapper.mapToRoomPopular
import com.dacuesta.architectcoders.framework.room.dao.FavoriteMovieDAO
import com.dacuesta.architectcoders.framework.room.dao.PopularMovieDAO
import com.dacuesta.architectcoders.framework.room.model.PopularMovie
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.dacuesta.architectcoders.domain.Movie as DomainMovie

internal class MoviesLocalDataSourceImpl : MoviesLocalDataSource, KoinComponent {
    private val popularMovieDAO by inject<PopularMovieDAO>()
    private val favoriteMovieDAO by inject<FavoriteMovieDAO>()

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