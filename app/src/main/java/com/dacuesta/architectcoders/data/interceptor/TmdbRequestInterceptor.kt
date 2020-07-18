package com.dacuesta.architectcoders.data.interceptor

import android.content.Context
import android.content.pm.PackageManager
import com.dacuesta.architectcoders.data.constant.TmdbConstant.API_KEY_QUERY
import okhttp3.Interceptor
import okhttp3.Response

class TmdbRequestInterceptor(
    private val context: Context
) : Interceptor {

    companion object {
        private const val METADATA_API_KEY = "tmdb_api_key"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(API_KEY_QUERY, getApiKey())
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun getApiKey() = context.packageManager
        .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA).metaData
        .getString(METADATA_API_KEY)

}