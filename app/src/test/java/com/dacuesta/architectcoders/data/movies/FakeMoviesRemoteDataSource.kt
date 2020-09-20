package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import arrow.core.right
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie

val fakeRemoteMovies = listOf(
    Movie(id = 1),
    Movie(id = 2),
    Movie(id = 3),
    Movie(id = 4)
)

class FakeMoviesRemoteDataSource : MoviesRemoteDataSource {
    override suspend fun getPopularMovies(): Either<Error, List<Movie>> =
        fakeRemoteMovies.right()
}