package com.dacuesta.architectcoders.data.moviedetail.di

import com.dacuesta.architectcoders.BuildConfig
import com.dacuesta.architectcoders.data.interceptor.RequestInterceptor
import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailRemoteDataSourceImpl
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val movieDetailDataModule = module {

    single {
        MovieDetailRepository(get())
    }

    factory<MovieDetailRemoteDataSource> {
        MovieDetailRemoteDataSourceImpl(get())
    }

    factory {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(MovieDetailService::class.java)
    }

}

private fun getClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    })
    .addInterceptor(RequestInterceptor())
    .build()