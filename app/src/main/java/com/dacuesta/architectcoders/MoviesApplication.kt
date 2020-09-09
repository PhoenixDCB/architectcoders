package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.dataModule
import com.dacuesta.architectcoders.framework.frameworkModule
import com.dacuesta.architectcoders.framework.permission.AppPermission
import com.dacuesta.architectcoders.movie.movieModule
import com.dacuesta.architectcoders.movies.moviesModule
import com.dacuesta.architectcoders.navigator.Navigator
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDi()
        initNavigator()
        initPermissions()
        initLogcat()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                frameworkModule,
                dataModule,
                appModule,
                moviesModule,
                movieModule
            )
        }
    }

    private fun initNavigator() {
        get<Navigator>()
    }

    private fun initPermissions() {
        get<AppPermission>()
    }

    private fun initLogcat() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}