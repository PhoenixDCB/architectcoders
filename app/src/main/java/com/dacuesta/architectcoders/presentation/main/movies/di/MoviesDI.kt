package com.dacuesta.architectcoders.presentation.main.movies.di

import com.dacuesta.architectcoders.presentation.main.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    viewModel {
        MoviesViewModel()
    }
}