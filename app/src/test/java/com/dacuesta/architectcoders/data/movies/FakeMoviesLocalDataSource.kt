package com.dacuesta.architectcoders.data.movies

import com.dacuesta.architectcoders.domain.Movie

class FakeMoviesLocalDataSource : MoviesLocalDataSource {

    private var popularMovies = emptyList<Movie>()
    private var favoriteMovies = emptyList<Movie>()

    override fun insertPopularMovies(movies: List<Movie>) {
        val pMovies = popularMovies.toMutableList()
        pMovies.addAll(movies)
        popularMovies = pMovies
    }

    override fun deleteAllPopularMovies() {
        popularMovies = emptyList()
    }

    override fun getAllPopularMovies(): List<Movie> =
        popularMovies

    override fun insertFavoriteMovie(movie: Movie) {
        val movies = favoriteMovies.toMutableList()
        movies.add(movie)
        favoriteMovies = movies
    }

    override fun deleteFavoriteMovie(movie: Movie) {
        val movies = favoriteMovies.toMutableList()
        movies.remove(movie)
        favoriteMovies = movies
    }

    override fun getAllFavoriteMovies(): List<Movie> =
        favoriteMovies

}