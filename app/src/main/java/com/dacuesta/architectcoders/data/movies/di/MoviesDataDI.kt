package com.dacuesta.architectcoders.data.movies.di

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.data.movies.remote.MoviesRemoteDataSource
import com.dacuesta.architectcoders.data.movies.remote.MoviesRemoteDataSourceImpl
import com.dacuesta.architectcoders.data.movies.remote.MoviesService
import org.koin.dsl.module
import retrofit2.Retrofit

val moviesDataModule = module {

    single {
        MoviesRepository(get())
    }

    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(get())
    }

    factory {
        get<Retrofit>().create(MoviesService::class.java)
    }

}