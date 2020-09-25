package com.dacuesta.architectcoders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.dacuesta.architectcoders.domain.model.Error
import com.dacuesta.architectcoders.domain.movies.GetPopularMovies
import com.dacuesta.architectcoders.domain.movies.model.Movie
import com.dacuesta.architectcoders.domain.movies.model.MoviesMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val getPopularMovies by inject<GetPopularMovies>()


    private val _moviesLiveData = MutableLiveData<Either<Error, List<Movie>>>()
    val moviesLiveData: LiveData<Either<Error, List<Movie>>>
        get() = _moviesLiveData


    init {
        viewModelScope.launch {
            getPopularMovies("US")
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _moviesLiveData.postValue(result.map { metadata ->
                        metadata.results
                    })
                }
        }
    }

}