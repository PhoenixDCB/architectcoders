package com.dacuesta.architectcoders.data.moviedetail.remote

import com.dacuesta.architectcoders.data.constant.TmdbConstant.DETAIL_MOVIE_ENDPOINT
import com.dacuesta.architectcoders.data.constant.TmdbConstant.DETAIL_MOVIE_ID_PATH
import com.dacuesta.architectcoders.data.moviedetail.dto.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET(DETAIL_MOVIE_ENDPOINT)
    suspend fun getMovieDetail(@Path(DETAIL_MOVIE_ID_PATH) id: Int) : Response<MovieDetail>

}