package com.dacuesta.architectcoders.framework.tmdb.di

import com.dacuesta.architectcoders.BuildConfig
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant.BASE_URL
import com.dacuesta.architectcoders.framework.tmdb.interceptor.RequestInterceptor
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val tmdbModule = module {
    single {
        get<Retrofit>().create(TmdbService::class.java)
    }

    single {
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
                    .addInterceptor(interceptor = RequestInterceptor(context = androidContext()))
                    .build()
            )
            .build()
    }
}