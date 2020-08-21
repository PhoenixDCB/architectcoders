package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.movies.Movie
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetFavoriteMovies : KoinComponent {

    private val repository by inject<MoviesRepository>()

    operator fun invoke() : Flow<List<Movie>> =
        repository.favoriteMovies

}