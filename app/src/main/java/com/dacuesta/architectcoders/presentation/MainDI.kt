package com.dacuesta.architectcoders.presentation

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel()
    }

}