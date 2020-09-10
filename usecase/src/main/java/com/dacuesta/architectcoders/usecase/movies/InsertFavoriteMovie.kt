package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie

class InsertFavoriteMovie(private val repository: MoviesRepository) {

    operator fun invoke(movie: Movie) {
        repository.insertFavoriteMovie(movie)
    }

}