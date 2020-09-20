package com.dacuesta.architectcoders.movies

import com.dacuesta.architectcoders.movies.favoritemovies.FavoriteMoviesFragment
import com.dacuesta.architectcoders.movies.favoritemovies.FavoriteMoviesViewModel
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesFragment
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesViewModel
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesModule = module {
    scope(named<MoviesActivity>()) {
        scoped { GetFavoriteMovies(repository = get()) }
        scoped { InsertFavoriteMovie(repository = get()) }
        scoped { DeleteFavoriteMovie(repository = get()) }
    }

    scope(named<PopularMoviesFragment>()) {
        viewModel {
            PopularMoviesViewModel(
                io = get(named("io")),
                main = get(named("main")),
                navigator = get(),
                getPopularMovies = get(),
                getFavoriteMovies = getScope(MoviesActivity.SCOPE_ID).get(),
                insertFavoriteMovie = getScope(MoviesActivity.SCOPE_ID).get(),
                deleteFavoriteMovie = getScope(MoviesActivity.SCOPE_ID).get()
            )
        }

        scoped { GetPopularMovies(repository = get()) }
    }

    scope(named<FavoriteMoviesFragment>()) {
        viewModel {
            FavoriteMoviesViewModel(
                io = get(named("io")),
                navigator = get(),
                getFavoriteMovies = getScope(MoviesActivity.SCOPE_ID).get(),
                deleteFavoriteMovie = getScope(MoviesActivity.SCOPE_ID).get()
            )
        }
    }
}