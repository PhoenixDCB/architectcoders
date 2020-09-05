package com.dacuesta.architectcoders.framework.tmdb.di

import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.URL_BASE
import com.dacuesta.architectcoders.framework.tmdb.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val tmdbModule = module {
    single {
        get<Retrofit>().create(TmdbService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.BODY
                        }
                    })
                    .addInterceptor(interceptor = RequestInterceptor())
                    .build()
            )
            .build()
    }
}