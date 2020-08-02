package com.dacuesta.architectcoders.domain.usecase.movies

import arrow.core.Either
import arrow.core.left
import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.MoviesState
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.atomic.AtomicBoolean

class GetPopularMovies : KoinComponent {

    @ExperimentalCoroutinesApi
    private val repository by inject<MoviesRepository>()

    private val isLoading = AtomicBoolean(false)
    private var page = 1
    private var totalPages = 1
    private var movies = emptyList<MovieEntity>()

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(): Flow<MoviesState<List<MovieEntity>>> = flow {
        when {
            isLoading.compareAndSet(false, true) && page <= totalPages -> {
                emit(MoviesState.Loading())
                repository.getPopularMovies("us", page).map { either ->
                    either.mapLeft { error ->
                        error
                    }.map { metadata ->
                        page = metadata.page + 1
                        totalPages = metadata.totalPages
                        movies = movies.toMutableList().apply { addAll(metadata.results) }.toList()
                        movies
                    }
                }.collect { either ->
                    either.fold(
                        { error ->
                            emit(MoviesState.Failure(error))
                        },
                        { movies ->
                            emit(MoviesState.Success(movies))
                        }
                    )
                    isLoading.set(false)
                }
            }
            page > totalPages -> {
                emit(MoviesState.Failure(ErrorEntity.Empty))
            }
            else -> {
                emit(MoviesState.Loading())
            }
        }
    }

}