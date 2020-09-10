package com.dacuesta.architectcoders.usecase.moviedetail

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository

class GetMovieDetail(private val repository: MovieDetailRepository) {

    suspend operator fun invoke(id: Int) = repository.getMovieDetail(id)

}