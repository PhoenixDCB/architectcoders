package com.dacuesta.architectcoders.domain.usecase.movies

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import org.koin.core.KoinComponent
import org.koin.core.inject

class DeleteFavoritePopularMovie : KoinComponent {

    private val repository by inject<MoviesRepository>()

    operator fun invoke(movie: MovieEntity) {
        repository.deleteFavoritePopularMovie(movie)
    }

}