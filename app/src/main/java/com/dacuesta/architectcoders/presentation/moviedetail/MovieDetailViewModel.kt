package com.dacuesta.architectcoders.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.dacuesta.architectcoders.domain.model.Error
import com.dacuesta.architectcoders.domain.moviedetail.GetMovieDetail
import com.dacuesta.architectcoders.domain.moviedetail.model.MovieDetail
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailViewModel : ViewModel(), KoinComponent {

    private val getMovieDetail by inject<GetMovieDetail>()

    private val _movieDetailLiveData = MutableLiveData<Either<Error, MovieDetail>>()
    val movieDetailLiveData: LiveData<Either<Error, MovieDetail>>
        get() = _movieDetailLiveData


    init {
        viewModelScope.launch {
            getMovieDetail(278).collect { result ->
                _movieDetailLiveData.postValue(result)
            }
        }
    }

}