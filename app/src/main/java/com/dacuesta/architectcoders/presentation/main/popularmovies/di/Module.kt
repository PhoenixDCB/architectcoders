package com.dacuesta.architectcoders.presentation.main.popularmovies.di

import com.dacuesta.architectcoders.presentation.main.popularmovies.PopularMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val popularMoviesModule = module {
    viewModel {
        PopularMoviesViewModel()
    }
}