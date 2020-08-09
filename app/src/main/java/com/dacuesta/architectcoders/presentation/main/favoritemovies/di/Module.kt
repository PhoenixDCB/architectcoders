package com.dacuesta.architectcoders.presentation.main.favoritemovies.di

import com.dacuesta.architectcoders.presentation.main.favoritemovies.FavoriteMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val favoriteMoviesModule = module {
    viewModel {
        FavoriteMoviesViewModel()
    }
}