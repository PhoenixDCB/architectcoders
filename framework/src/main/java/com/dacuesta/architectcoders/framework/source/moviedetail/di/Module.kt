package com.dacuesta.architectcoders.framework.source.moviedetail.di

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.framework.source.moviedetail.MovieDetailRemoteDataSourceImpl
import org.koin.dsl.module

val movieDetailSourceModule = module {
    single<MovieDetailRemoteDataSource> {
        MovieDetailRemoteDataSourceImpl()
    }
}