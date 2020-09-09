package com.dacuesta.architectcoders.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.MovieDetail
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.usecase.moviedetail.GetMovieDetail
import com.dacuesta.architectcoders.utils.toMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val entry: MovieDetailEntry,
    private val navigator: Navigator,
    private val getMovieDetail: GetMovieDetail
) : ViewModel() {

    private val _movieLD = MutableLiveData<MovieDetailModel>()
    val movieLD: LiveData<MovieDetailModel>
        get() = _movieLD

    private var movie = MovieDetail()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            invokeGetMovieDetail()
        }
    }

    fun retryClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            invokeGetMovieDetail()
        }
    }

    private suspend fun invokeGetMovieDetail() {
        _movieLD.postValue(MovieDetailModel.Loader)
        getMovieDetail(entry.id).fold(::handleError, ::handleSuccess)
    }

    private fun handleError(error: Error) {
        _movieLD.postValue(MovieDetailModel.Result(movie))
        viewModelScope.launch(Dispatchers.Main) {
            navigator.toast(error.toMessage())
        }
    }

    private fun handleSuccess(movie: MovieDetail) {
        this.movie = movie
        _movieLD.postValue(MovieDetailModel.Result(movie))
    }

}