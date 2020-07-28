package com.dacuesta.architectcoders.domain.usecase.moviedetail

import com.dacuesta.architectcoders.data.repository.moviedetail.MovieDetailRepository
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieDetail : KoinComponent {

    private val repository by inject<MovieDetailRepository>()

    suspend operator fun invoke(id: Int) = repository.getMovieDetail(id)

}