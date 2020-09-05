package com.dacuesta.architectcoders.framework.room.di

import androidx.room.Room
import com.dacuesta.architectcoders.framework.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        get<AppDatabase>().popularMovieDAO()
    }

    single {
        get<AppDatabase>().favoriteMovieDAO()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
}