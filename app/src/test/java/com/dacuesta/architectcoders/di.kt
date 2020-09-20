package com.dacuesta.architectcoders

import com.dacuesta.architectcoders.data.movies.FakeMoviesLocalDataSource
import com.dacuesta.architectcoders.data.movies.FakeMoviesRemoteDataSource
import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesViewModel
import com.dacuesta.architectcoders.navigator.FakeNavigator
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val testFrameworkModule = module {
    single<MoviesRemoteDataSource> { FakeMoviesRemoteDataSource() }
    single<MoviesLocalDataSource> { FakeMoviesLocalDataSource() }
}

val testAppModule = module {
    single(named("io")) { Dispatchers.Unconfined }
    single(named("main")) { Dispatchers.Unconfined }

    single<Navigator> { FakeNavigator() }
}

val testMoviesModule = module {
    factory { GetPopularMovies(repository = get()) }
    factory { GetFavoriteMovies(repository = get()) }
    factory { InsertFavoriteMovie(repository = get()) }
    factory { DeleteFavoriteMovie(repository = get()) }

    factory {
        PopularMoviesViewModel(
            io = get(named("io")),
            main = get(named("main")),
            navigator = get(),
            getPopularMovies = get(),
            getFavoriteMovies = get(),
            insertFavoriteMovie = get(),
            deleteFavoriteMovie = get()
        )
    }
}
