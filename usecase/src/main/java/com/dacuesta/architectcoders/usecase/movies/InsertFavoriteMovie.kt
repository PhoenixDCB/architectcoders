package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie
import org.koin.core.KoinComponent
import org.koin.core.inject

class InsertFavoriteMovie : KoinComponent {

    private val repository by inject<MoviesRepository>()

    operator fun invoke(movie: Movie) {
        repository.insertFavoriteMovie(movie)
    }

}