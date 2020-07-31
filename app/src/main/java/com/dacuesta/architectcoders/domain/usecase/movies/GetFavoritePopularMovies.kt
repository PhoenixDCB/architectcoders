package com.dacuesta.architectcoders.domain.usecase.movies

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetFavoritePopularMovies : KoinComponent {

    private val repository by inject<MoviesRepository>()

    @ExperimentalCoroutinesApi
    operator fun invoke() = repository.favoritePopularMovies

}