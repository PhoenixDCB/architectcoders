package com.dacuesta.architectcoders.data.moviedetail.di

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailRemoteDataSourceImpl
import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailService
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailDataModule = module {

    single {
        MovieDetailRepository(get())
    }

    factory<MovieDetailRemoteDataSource> {
        MovieDetailRemoteDataSourceImpl(get())
    }

    factory {
        get<Retrofit>().create(MovieDetailService::class.java)
    }

}