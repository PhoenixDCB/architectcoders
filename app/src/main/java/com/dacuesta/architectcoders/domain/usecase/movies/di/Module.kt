package com.dacuesta.architectcoders.domain.usecase.movies.di

import com.dacuesta.architectcoders.domain.usecase.movies.DeleteFavoritePopularMovie
import com.dacuesta.architectcoders.domain.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.GetFavoritePopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.InsertFavoritePopularMovie
import org.koin.dsl.module

val moviesUseCaseModule = module {

    factory {
        GetPopularMovies()
    }

    factory {
        GetFavoritePopularMovies()
    }

    factory {
        InsertFavoritePopularMovie()
    }

    factory {
        DeleteFavoritePopularMovie()
    }

}