package com.dacuesta.architectcoders.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", "1602c269235b8f7042f5033f2793e37d").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}