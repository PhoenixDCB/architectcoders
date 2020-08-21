package com.dacuesta.architectcoders.framework.tmdb.constant

internal object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val MOVIE_DETAIL_ID_PATH = "id"

    const val API_KEY_QUERY = "api_key"
    const val REGION_QUERY = "region"
    const val PAGE_QUERY = "page"

    const val POPULAR_MOVIES_ENDPOINT = "movie/popular"
    const val MOVIE_DETAIL_ENDPOINT = "movie/{$MOVIE_DETAIL_ID_PATH}"
}
