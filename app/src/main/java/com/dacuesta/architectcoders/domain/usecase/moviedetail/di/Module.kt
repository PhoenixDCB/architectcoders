package com.dacuesta.architectcoders.domain.usecase.moviedetail.di

import com.dacuesta.architectcoders.domain.usecase.moviedetail.GetMovieDetail
import org.koin.dsl.module

val movieDetailUseCaseModule = module {

    factory {
        GetMovieDetail()
    }

}