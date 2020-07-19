package com.dacuesta.architectcoders.data.moviedetail.remote

import com.dacuesta.architectcoders.data.common.constant.Constant.MOVIE_DETAIL_ENDPOINT
import com.dacuesta.architectcoders.data.common.constant.Constant.MOVIE_DETAIL_ID_PATH
import com.dacuesta.architectcoders.data.common.model.moviedetail.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET(MOVIE_DETAIL_ENDPOINT)
    suspend fun getMovieDetail(@Path(MOVIE_DETAIL_ID_PATH) id: Int) : Response<MovieDetail>

}