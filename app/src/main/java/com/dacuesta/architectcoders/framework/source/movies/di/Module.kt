package com.dacuesta.architectcoders.framework.source.movies.di

import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.framework.source.movies.MoviesLocalDataSourceImpl
import com.dacuesta.architectcoders.framework.source.movies.MoviesRemoteDataSourceImpl
import org.koin.dsl.module

val moviesSourceModule = module {
    single<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl()
    }

    single<MoviesLocalDataSource> {
        MoviesLocalDataSourceImpl()
    }
}