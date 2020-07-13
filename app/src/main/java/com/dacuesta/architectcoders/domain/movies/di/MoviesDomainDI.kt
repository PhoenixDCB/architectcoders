package com.dacuesta.architectcoders.domain.movies.di

import com.dacuesta.architectcoders.domain.movies.GetPopularMovies
import org.koin.dsl.module

val moviesDomainModule = module {

    factory {
        GetPopularMovies()
    }

}