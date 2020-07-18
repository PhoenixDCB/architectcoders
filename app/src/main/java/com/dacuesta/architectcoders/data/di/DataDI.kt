package com.dacuesta.architectcoders.data.di

import com.dacuesta.architectcoders.BuildConfig
import com.dacuesta.architectcoders.data.constant.KoinConstant.TMDB_RETROFIT_KEY
import com.dacuesta.architectcoders.data.constant.TmdbConstant.BASE_URL
import com.dacuesta.architectcoders.data.interceptor.TmdbRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory(named(TMDB_RETROFIT_KEY)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    })
                    .addInterceptor(interceptor = TmdbRequestInterceptor(context = androidContext()))
                    .build()
            )
            .build()
    }
}