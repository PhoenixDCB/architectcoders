package com.dacuesta.architectcoders.usecase.movies.di

import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import org.koin.dsl.module

val moviesUseCaseModule = module {

    factory {
        GetPopularMovies()
    }

    factory {
        GetFavoriteMovies()
    }

    factory {
        InsertFavoriteMovie()
    }

    factory {
        DeleteFavoriteMovie()
    }

}