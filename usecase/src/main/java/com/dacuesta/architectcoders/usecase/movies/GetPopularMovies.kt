package com.dacuesta.architectcoders.usecase.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPopularMovies : KoinComponent {

    private val repository by inject<MoviesRepository>()

    suspend operator fun invoke(page: Int): Either<Error, MoviesMetadata> =
        repository.getPopularMovies(page)

}