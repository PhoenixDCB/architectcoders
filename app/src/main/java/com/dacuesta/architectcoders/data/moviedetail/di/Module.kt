package com.dacuesta.architectcoders.data.moviedetail.di

import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRepository
import org.koin.dsl.module

val movieDetailRepositoryModule = module {
    single {
        MovieDetailRepository()
    }
}