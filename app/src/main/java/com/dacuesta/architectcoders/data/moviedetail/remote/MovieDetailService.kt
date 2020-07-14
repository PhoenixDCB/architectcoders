package com.dacuesta.architectcoders.data.moviedetail.remote

import com.dacuesta.architectcoders.data.moviedetail.dto.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int) : Response<MovieDetail>

}