package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.flow.Flow

class GetFavoriteMovies(private val repository: MoviesRepository) {

    operator fun invoke(): Flow<List<Movie>> =
        repository.favoriteMovies

}