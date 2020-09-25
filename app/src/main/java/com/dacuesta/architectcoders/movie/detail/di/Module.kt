package com.dacuesta.architectcoders.movie.detail.di

import com.dacuesta.architectcoders.movie.detail.MovieDetailEntry
import com.dacuesta.architectcoders.movie.detail.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailModule = module {
    viewModel {
        (entry: MovieDetailEntry) -> MovieDetailViewModel(entry)
    }
}