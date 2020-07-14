package com.dacuesta.architectcoders.presentation.moviedetail

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailModule = module {

    viewModel {
        MovieDetailViewModel()
    }

}