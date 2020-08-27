package com.dacuesta.architectcoders.framework.mapper

import com.dacuesta.architectcoders.domain.moviedetail.MovieDetail as DomainMovieDetail
import com.dacuesta.architectcoders.framework.tmdb.model.moviedetail.MovieDetail as TmdbMovieDetail

internal fun map(detail: TmdbMovieDetail) =
    DomainMovieDetail(
        title = detail.title,
        backdropImageUrl = "https://image.tmdb.org/t/p/w500${detail.backdropPath}",
        overview = detail.overview,
        releaseDate = detail.releaseDate
    )
