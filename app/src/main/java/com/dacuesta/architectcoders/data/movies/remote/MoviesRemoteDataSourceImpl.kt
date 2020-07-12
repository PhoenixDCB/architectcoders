package com.dacuesta.architectcoders.data.movies.remote

import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.dto.Error
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MoviesRemoteDataSourceImpl(
    private val service: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(region: String) = flow{
        val response = service.getPopularMovies(region)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                emit(body.results.right())
            } ?: emit(Error.NullBody.left())
        } else {
            when (response.code()) {
                401 -> {
                    emit(Error.Unauthorized.left())
                }
                500 -> {
                    emit(Error.Server.left())
                }
                else -> {
                    emit(Error.Unknown.left())
                }
            }
        }
    }.catch {
        emit(Error.Unknown.left())
    }

}