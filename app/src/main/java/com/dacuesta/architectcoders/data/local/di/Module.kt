package com.dacuesta.architectcoders.data.local.di

import androidx.room.Room
import com.dacuesta.architectcoders.data.local.AppDatabase
import com.dacuesta.architectcoders.data.local.movie.MovieLocalDataSource
import com.dacuesta.architectcoders.data.local.movie.MovieLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    single<MovieLocalDataSource> {
        MovieLocalDataSourceImpl()
    }

    single {
        get<AppDatabase>().movieDAO()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
}