package com.dacuesta.architectcoders.domain.usecase.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.movies.MoviesMetadataEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPopularMovies : KoinComponent {

    @ExperimentalCoroutinesApi
    private val repository by inject<MoviesRepository>()

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(page: Int): Either<ErrorEntity, MoviesMetadataEntity> =
        repository.getPopularMovies("us", page)

}