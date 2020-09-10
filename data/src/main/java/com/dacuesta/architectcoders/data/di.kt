package com.dacuesta.architectcoders.data

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import org.koin.dsl.module

val dataModule = module {
    single { MoviesRepository(remoteDataSource = get(), localDataSource = get()) }
    single { MovieDetailRepository(remoteDataSource = get()) }
}