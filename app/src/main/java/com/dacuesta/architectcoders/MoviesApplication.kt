package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.common.di.dataModule
import com.dacuesta.architectcoders.data.moviedetail.di.movieDetailDataModule
import com.dacuesta.architectcoders.data.movies.di.moviesDataModule
import com.dacuesta.architectcoders.domain.moviedetail.di.movieDetailDomainModule
import com.dacuesta.architectcoders.domain.movies.di.moviesDomainModule
import com.dacuesta.architectcoders.presentation.moviedetail.movieDetailModule
import com.dacuesta.architectcoders.presentation.movies.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDi()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    dataModule,
                    moviesDataModule,
                    movieDetailDataModule,

                    moviesDomainModule,
                    movieDetailDomainModule,

                    moviesModule,
                    movieDetailModule
                )
            )
        }
    }
}