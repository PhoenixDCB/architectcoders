package com.dacuesta.architectcoders.domain.usecase.movies

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class DeleteFavoritePopularMovie : KoinComponent {

    @ExperimentalCoroutinesApi
    private val repository by inject<MoviesRepository>()

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(movie: MovieEntity) = repository.deleteFavoritePopularMovie(movie)

}