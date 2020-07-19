package com.dacuesta.architectcoders.data.movies.remote

import com.dacuesta.architectcoders.data.common.constant.Constant.POPULAR_MOVIES_ENDPOINT
import com.dacuesta.architectcoders.data.common.constant.Constant.REGION_QUERY
import com.dacuesta.architectcoders.data.common.model.movies.MoviesMetadata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(POPULAR_MOVIES_ENDPOINT)
    suspend fun getPopularMovies(@Query(REGION_QUERY) region: String) : Response<MoviesMetadata>

}