package com.dacuesta.architectcoders.domain.usecase.movies.di

import com.dacuesta.architectcoders.domain.usecase.movies.GetPopularMovies
import org.koin.dsl.module

val moviesUseCaseModule = module {

    factory {
        GetPopularMovies()
    }

}