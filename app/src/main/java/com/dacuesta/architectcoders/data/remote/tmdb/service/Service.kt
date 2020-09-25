package com.dacuesta.architectcoders.data.remote.tmdb.service

import com.dacuesta.architectcoders.data.remote.dto.moviedetail.MovieDetailDTO
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant.POPULAR_MOVIES_ENDPOINT
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant.REGION_QUERY
import com.dacuesta.architectcoders.data.remote.dto.movies.MoviesMetadataDTO
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant.MOVIE_DETAIL_ENDPOINT
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant.PAGE_QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface Service {

    @GET(POPULAR_MOVIES_ENDPOINT)
    suspend fun getPopularMovies(
        @Query(REGION_QUERY) region: String,
        @Query(PAGE_QUERY) page: Int
    ) : Response<MoviesMetadataDTO>

    @GET(MOVIE_DETAIL_ENDPOINT)
    suspend fun getMovieDetail(@Path(Constant.MOVIE_DETAIL_ID_PATH) id: Int) : Response<MovieDetailDTO>

}