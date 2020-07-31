package com.dacuesta.architectcoders.data.remote.tmdb.di

import com.dacuesta.architectcoders.BuildConfig
import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSourceImpl
import com.dacuesta.architectcoders.data.remote.tmdb.interceptor.RequestInterceptor
import com.dacuesta.architectcoders.data.remote.tmdb.constant.Constant.BASE_URL
import com.dacuesta.architectcoders.data.remote.tmdb.service.Service
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val tmdbRemoteDataSourceModule = module {

    single<TmdbRemoteDataSource> {
        TmdbRemoteDataSourceImpl()
    }

    single {
        get<Retrofit>().create(Service::class.java)
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