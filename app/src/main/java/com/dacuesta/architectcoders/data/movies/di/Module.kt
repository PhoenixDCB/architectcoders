package com.dacuesta.architectcoders.data.movies.di

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import org.koin.dsl.module

val moviesRepositoryModule = module {
    single {
        MoviesRepository()
    }
}