package com.dacuesta.architectcoders.domain.usecase.movies

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPopularMovies : KoinComponent {

    private val repository by inject<MoviesRepository>()

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(region: String) = repository.getPopularMovies(region)

}