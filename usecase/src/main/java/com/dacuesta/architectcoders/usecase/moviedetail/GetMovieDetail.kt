package com.dacuesta.architectcoders.usecase.moviedetail

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieDetail : KoinComponent {

    private val repository by inject<MovieDetailRepository>()

    suspend operator fun invoke(id: Int) = repository.getMovieDetail(id)

}