package com.dacuesta.architectcoders.usecase.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies.Method.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPopularMovies : KoinComponent {

    enum class Method {
        CURRENT_MOVIES, REFRESH_CURRENT_MOVIES, NEXT_MOVIES
    }

    private val repository by inject<MoviesRepository>()

    suspend operator fun invoke(method: Method): Either<Error, List<Movie>> =
        when (method) {
            CURRENT_MOVIES -> {
                repository.getCurrentPopularMovies(false)
            }
            REFRESH_CURRENT_MOVIES -> {
                repository.getCurrentPopularMovies(true)
            }
            NEXT_MOVIES -> {
                repository.getNextPopularMovies()
            }
        }
}