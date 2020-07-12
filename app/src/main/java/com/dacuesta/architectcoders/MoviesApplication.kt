package com.dacuesta.architectcoders

import android.app.Application
import com.dacuesta.architectcoders.data.movies.di.moviesModule
import com.dacuesta.architectcoders.presentation.mainModule
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
            modules(listOf(
                moviesModule,
                mainModule
            ))
        }
    }
}