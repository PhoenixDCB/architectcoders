package com.dacuesta.architectcoders.mapper

import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.movie.MovieEntry
import com.dacuesta.architectcoders.movie.detail.MovieDetailEntry

fun map(movie: Movie) = MovieEntry(id = movie.id, title = movie.title)

fun map(entry: MovieEntry) = MovieDetailEntry(id = entry.id, title = entry.title)