package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.remote.tmdb.di.tmdbRemoteDataSourceModule
import com.dacuesta.architectcoders.data.repository.moviedetail.di.movieDetailRepositoryModule
import com.dacuesta.architectcoders.data.repository.movies.di.moviesRepositoryModule
import com.dacuesta.architectcoders.domain.usecase.moviedetail.di.movieDetailUseCaseModule
import com.dacuesta.architectcoders.domain.usecase.movies.di.moviesUseCaseModule
import com.dacuesta.architectcoders.presentation.main.popularmovies.di.popularMoviesModule
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
                    tmdbRemoteDataSourceModule,

                    moviesRepositoryModule,
                    movieDetailRepositoryModule,

                    moviesUseCaseModule,
                    movieDetailUseCaseModule,

                    popularMoviesModule
                )
            )
        }
    }
}