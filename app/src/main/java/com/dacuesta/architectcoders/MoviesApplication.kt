package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.local.di.localDataSourceModule
import com.dacuesta.architectcoders.data.remote.tmdb.di.tmdbRemoteDataSourceModule
import com.dacuesta.architectcoders.data.repository.moviedetail.di.movieDetailRepositoryModule
import com.dacuesta.architectcoders.data.repository.movies.di.moviesRepositoryModule
import com.dacuesta.architectcoders.domain.usecase.moviedetail.di.movieDetailUseCaseModule
import com.dacuesta.architectcoders.domain.usecase.movies.di.moviesUseCaseModule
import com.dacuesta.architectcoders.presentation.main.favoritemovies.di.favoriteMoviesModule
import com.dacuesta.architectcoders.presentation.main.popularmovies.di.popularMoviesModule
import com.dacuesta.architectcoders.presentation.navigator.Navigator
import com.dacuesta.architectcoders.presentation.navigator.di.navigatorModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        initDi()
        initNavigator()
        initLogcat()
    }

    @ExperimentalCoroutinesApi
    private fun initDi() {
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    tmdbRemoteDataSourceModule,
                    localDataSourceModule,

                    moviesRepositoryModule,
                    movieDetailRepositoryModule,

                    moviesUseCaseModule,
                    movieDetailUseCaseModule,

                    navigatorModule,

                    popularMoviesModule,
                    favoriteMoviesModule
                )
            )
        }
    }

    private fun initNavigator() {
        get<Navigator>()
    }

    private fun initLogcat() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}