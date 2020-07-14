package com.dacuesta.architectcoders.presentation.movies

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {

    viewModel {
        MoviesViewModel()
    }

}