package com.dacuesta.architectcoders.framework

import androidx.room.Room
import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import com.dacuesta.architectcoders.framework.location.AppLocation
import com.dacuesta.architectcoders.framework.permission.AppPermission
import com.dacuesta.architectcoders.framework.room.AppDatabase
import com.dacuesta.architectcoders.framework.source.moviedetail.MovieDetailRemoteDataSourceImpl
import com.dacuesta.architectcoders.framework.source.movies.MoviesLocalDataSourceImpl
import com.dacuesta.architectcoders.framework.source.movies.MoviesRemoteDataSourceImpl
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.tmdb.constant.Constant
import com.dacuesta.architectcoders.framework.tmdb.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val frameworkModule = module {
    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(
            context = androidContext(),
            appLocation = get(),
            appGeoCoder = get(),
            tmdbService = get()
        )
    }
    factory<MoviesLocalDataSource> {
        MoviesLocalDataSourceImpl(
            popularMovieDAO = get(),
            favoriteMovieDAO = get()
        )
    }

    factory<MovieDetailRemoteDataSource> { MovieDetailRemoteDataSourceImpl(tmdbService = get()) }

    factory { AppGeoCoder(context = androidContext()) }

    factory { AppLocation(context = androidContext(), appPermission = get()) }

    single { AppPermission(context = androidContext()) }

    factory { get<AppDatabase>().popularMovieDAO() }
    factory { get<AppDatabase>().favoriteMovieDAO() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    factory { get<Retrofit>().create(TmdbService::class.java) }
    factory {
        Retrofit.Builder()
            .baseUrl(get<String>(named("baseUrl")))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(interceptor = RequestInterceptor())
            .build()
    }
    single(named("baseUrl")) { Constant.URL_BASE }
}