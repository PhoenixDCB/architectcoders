package com.dacuesta.architectcoders.usecase.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie

class GetPopularMovies(private val repository: MoviesRepository) {

    suspend operator fun invoke(refresh: Boolean): Either<Error, List<Movie>> =
        repository.getPopularMovies(refresh)
}