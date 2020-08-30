package com.dacuesta.architectcoders.framework.tmdb.constant

internal object Constant {

    const val VALUE_API_KEY = "1602c269235b8f7042f5033f2793e37d"

    const val URL_BASE = "https://api.themoviedb.org/3/"

    const val URL_PARAM_MOVIE_DETAIL_ID = "id"

    const val ENDPOINT_POPULAR_MOVIES = "movie/popular"
    const val ENDPOINT_MOVIE_DETAIL = "movie/{$URL_PARAM_MOVIE_DETAIL_ID}"

    const val QUERY_PARAM_API_KEY = "api_key"
    const val QUERY_PARAM_REGION = "region"
    const val QUERY_PARAM_PAGE = "page"

}