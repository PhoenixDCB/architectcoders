package com.dacuesta.architectcoders.data.constant

object TmdbConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val DETAIL_MOVIE_ID_PATH = "id"
    const val API_KEY_QUERY = "api_key"
    const val REGION_QUERY = "region"

    const val POPULAR_MOVIES_ENDPOINT = "movie/popular"
    const val DETAIL_MOVIE_ENDPOINT = "movie/{$DETAIL_MOVIE_ID_PATH}"
}
