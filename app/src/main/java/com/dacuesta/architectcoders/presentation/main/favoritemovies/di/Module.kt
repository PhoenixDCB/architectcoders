package com.dacuesta.architectcoders.presentation.main.favoritemovies.di

import com.dacuesta.architectcoders.presentation.main.favoritemovies.FavoriteMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteMoviesModule = module {
    viewModel {
        FavoriteMoviesViewModel()
    }
}