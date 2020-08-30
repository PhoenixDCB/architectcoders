package com.dacuesta.architectcoders.framework.tmdb

import com.dacuesta.architectcoders.framework.tmdb.model.moviedetail.MovieDetail
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.ENDPOINT_POPULAR_MOVIES
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.QUERY_PARAM_REGION
import com.dacuesta.architectcoders.framework.tmdb.model.movies.MoviesMetadata
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.ENDPOINT_MOVIE_DETAIL
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.QUERY_PARAM_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TmdbService {

    @GET(ENDPOINT_POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(QUERY_PARAM_REGION) region: String,
        @Query(QUERY_PARAM_PAGE) page: Int
    ): Response<MoviesMetadata>

    @GET(ENDPOINT_MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path(Constant.URL_PARAM_MOVIE_DETAIL_ID) id: Int): Response<MovieDetail>

}