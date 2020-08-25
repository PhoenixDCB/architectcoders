package com.dacuesta.architectcoders.movies.popularmovies.di

import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularMoviesModule = module {
    viewModel {
        PopularMoviesViewModel()
    }
}