package com.dacuesta.architectcoders.data.repository.moviedetail.di

import com.dacuesta.architectcoders.data.repository.moviedetail.MovieDetailRepository
import org.koin.dsl.module

val movieDetailRepositoryModule = module {

    single {
        MovieDetailRepository(get())
    }

}