package com.dacuesta.architectcoders.data.movies.remote

import com.dacuesta.architectcoders.data.movies.dto.MoviesMetadata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("region") region: String) : Response<MoviesMetadata>

}