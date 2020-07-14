package com.dacuesta.architectcoders.domain.moviedetail

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import com.dacuesta.architectcoders.domain.mapper.map
import com.dacuesta.architectcoders.domain.moviedetail.mapper.map
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieDetail : KoinComponent {

    private val movieDetailRepository by inject<MovieDetailRepository>()

    suspend operator fun invoke(id: Int) = movieDetailRepository.getMovieDetail(id)
        .map { result ->
            result.mapLeft { error ->
                error.map()
            }.map { movieDetail ->
                movieDetail.map()
            }
        }

}