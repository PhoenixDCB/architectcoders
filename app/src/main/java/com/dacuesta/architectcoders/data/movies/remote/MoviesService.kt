package com.dacuesta.architectcoders.data.movies.remote

import com.dacuesta.architectcoders.data.movies.model.MoviesMetadata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular?api_key=1602c269235b8f7042f5033f2793e37d")
    suspend fun getPopularMovies(@Query("region") region: String) : Response<MoviesMetadata>

}