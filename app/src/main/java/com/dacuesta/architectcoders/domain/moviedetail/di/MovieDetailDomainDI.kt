package com.dacuesta.architectcoders.domain.moviedetail.di

import com.dacuesta.architectcoders.domain.moviedetail.GetMovieDetail
import org.koin.dsl.module

val movieDetailDomainModule = module {

    factory {
        GetMovieDetail()
    }

}