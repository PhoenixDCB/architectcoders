package com.dacuesta.architectcoders.movie

import com.dacuesta.architectcoders.movie.detail.MovieDetailEntry
import com.dacuesta.architectcoders.movie.detail.MovieDetailFragment
import com.dacuesta.architectcoders.movie.detail.MovieDetailViewModel
import com.dacuesta.architectcoders.usecase.moviedetail.GetMovieDetail
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    scope<MovieDetailFragment> {
        viewModel { (entry: MovieDetailEntry) ->
            MovieDetailViewModel(
                entry = entry,
                navigator = get(),
                getMovieDetail = get()
            )
        }

        scoped { GetMovieDetail() }
    }
}