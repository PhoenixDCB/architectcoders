package com.dacuesta.architectcoders.main.popularmovies.di

import com.dacuesta.architectcoders.main.popularmovies.PopularMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val popularMoviesModule = module {
    viewModel {
        PopularMoviesViewModel()
    }
}