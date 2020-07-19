package com.dacuesta.architectcoders.domain.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.common.extension.map
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPopularMovies : KoinComponent {

    private val moviesRepository by inject<MoviesRepository>()

    suspend operator fun invoke(region: String) = moviesRepository.getPopularMovies(region)
        .map { result ->
            result.mapLeft { error ->
                error.map()
            }.map { metadata ->
                metadata.map()
            }
        }

}