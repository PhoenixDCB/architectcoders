package com.dacuesta.architectcoders.movies.favoritemovies.di

import com.dacuesta.architectcoders.movies.favoritemovies.FavoriteMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteMoviesModule = module {
    viewModel {
        FavoriteMoviesViewModel()
    }
}