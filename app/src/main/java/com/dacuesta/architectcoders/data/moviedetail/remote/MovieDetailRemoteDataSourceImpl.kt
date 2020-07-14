package com.dacuesta.architectcoders.data.moviedetail.remote

import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.model.Error
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRemoteDataSourceImpl(
    private val service: MovieDetailService
) : MovieDetailRemoteDataSource {

    override suspend fun getMovieDetail(id: Int) = flow {
        val response = service.getMovieDetail(id)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                emit(body.right())
            } ?: emit(Error.Empty.left())
        } else {
            when (response.code()) {
                401 -> {
                    emit(Error.Unauthorized.left())
                }
                422 -> {
                    Error.Empty.left()
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