package com.dacuesta.architectcoders.movie

import com.dacuesta.architectcoders.movie.detail.MovieDetailEntry
import com.dacuesta.architectcoders.movie.detail.MovieDetailFragment
import com.dacuesta.architectcoders.movie.detail.MovieDetailViewModel
import com.dacuesta.architectcoders.usecase.moviedetail.GetMovieDetail
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
    scope<MovieDetailFragment> {
        viewModel { (entry: MovieDetailEntry) ->
            MovieDetailViewModel(
                io = get(),
                main = get(),
                entry = entry,
                navigator = get(),
                getMovieDetail = get()
            )
        }

        scoped { GetMovieDetail(repository = get()) }
    }
}