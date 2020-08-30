package com.dacuesta.architectcoders.framework.tmdb.interceptor

import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.QUERY_PARAM_API_KEY
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.VALUE_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(QUERY_PARAM_API_KEY, VALUE_API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}