package com.dacuesta.architectcoders.data.moviedetail.remote

import com.dacuesta.architectcoders.data.moviedetail.dto.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{id}?api_key=1602c269235b8f7042f5033f2793e37d")
    suspend fun getMovieDetail(@Path("id") id: Int) : Response<MovieDetail>

}