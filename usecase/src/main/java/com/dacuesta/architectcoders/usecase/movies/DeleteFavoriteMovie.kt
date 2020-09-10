package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie

class DeleteFavoriteMovie(private val repository: MoviesRepository) {

    operator fun invoke(movie: Movie) {
        repository.deleteFavoriteMovie(movie)
    }

}