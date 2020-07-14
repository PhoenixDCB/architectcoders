package com.dacuesta.architectcoders.data.movies.di

import com.dacuesta.architectcoders.BuildConfig
import com.dacuesta.architectcoders.data.interceptor.RequestInterceptor
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.data.movies.remote.MoviesRemoteDataSource
import com.dacuesta.architectcoders.data.movies.remote.MoviesRemoteDataSourceImpl
import com.dacuesta.architectcoders.data.movies.remote.MoviesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moviesDataModule = module {

    single {
        MoviesRepository(get())
    }

    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(get())
    }

    factory {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
            .create(MoviesService::class.java)
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