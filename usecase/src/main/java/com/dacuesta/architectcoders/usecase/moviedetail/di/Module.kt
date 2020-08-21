package com.dacuesta.architectcoders.usecase.moviedetail.di

import com.dacuesta.architectcoders.usecase.moviedetail.GetMovieDetail
import org.koin.dsl.module

val movieDetailUseCaseModule = module {

    factory {
        GetMovieDetail()
    }

}