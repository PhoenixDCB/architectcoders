package com.dacuesta.architectcoders.domain.usecase.movies

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetFavoritePopularMovies : KoinComponent {

    private val repository by inject<MoviesRepository>()

    operator fun invoke() : Flow<List<MovieEntity>> =
        repository.favoritePopularMovies

}