package com.dacuesta.architectcoders.data.repository.movies.di

import com.dacuesta.architectcoders.data.repository.movies.MoviesRepository
import org.koin.dsl.module

val moviesRepositoryModule = module {

    single {
        MoviesRepository()
    }

}