package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.moviedetail.di.movieDetailRepositoryModule
import com.dacuesta.architectcoders.data.movies.di.moviesRepositoryModule
import com.dacuesta.architectcoders.framework.room.di.roomModule
import com.dacuesta.architectcoders.framework.source.moviedetail.di.movieDetailSourceModule
import com.dacuesta.architectcoders.framework.source.movies.di.moviesSourceModule
import com.dacuesta.architectcoders.framework.tmdb.di.tmdbModule
import com.dacuesta.architectcoders.movie.detail.di.movieDetailModule
import com.dacuesta.architectcoders.usecase.moviedetail.di.movieDetailUseCaseModule
import com.dacuesta.architectcoders.usecase.movies.di.moviesUseCaseModule
import com.dacuesta.architectcoders.movies.favoritemovies.di.favoriteMoviesModule
import com.dacuesta.architectcoders.movies.popularmovies.di.popularMoviesModule
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.navigator.di.navigatorModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDi()
        initNavigator()
        initLogcat()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                listOf(
                    tmdbModule,
                    roomModule,

                    moviesSourceModule,
                    movieDetailSourceModule,

                    moviesRepositoryModule,
                    movieDetailRepositoryModule,

                    moviesUseCaseModule,
                    movieDetailUseCaseModule,

                    navigatorModule,
                    popularMoviesModule,
                    favoriteMoviesModule,
                    movieDetailModule
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