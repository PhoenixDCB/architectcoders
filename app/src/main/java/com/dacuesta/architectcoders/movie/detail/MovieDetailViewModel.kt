package com.dacuesta.architectcoders.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.MovieDetail
import com.dacuesta.architectcoders.navigator.NavigatorImpl
import com.dacuesta.architectcoders.usecase.moviedetail.GetMovieDetail
import com.dacuesta.architectcoders.utils.toMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val io: CoroutineDispatcher,
    private val main: MainCoroutineDispatcher,
    private val entry: MovieDetailEntry,
    private val navigator: NavigatorImpl,
    private val getMovieDetail: GetMovieDetail
) : ViewModel() {

    private val _movieLD = MutableLiveData<MovieDetailModel>()
    val movieLD: LiveData<MovieDetailModel>
        get() = _movieLD

    private var movie = MovieDetail()

    init {
        viewModelScope.launch(io) {
            invokeGetMovieDetail()
        }
    }

    fun retryClicked() {
        viewModelScope.launch(io) {
            invokeGetMovieDetail()
        }
    }

    private suspend fun invokeGetMovieDetail() {
        _movieLD.postValue(MovieDetailModel.Loader)
        getMovieDetail(entry.id).fold(::handleError, ::handleSuccess)
    }

    private fun handleError(error: Error) {
        _movieLD.postValue(MovieDetailModel.Result(movie))
        viewModelScope.launch(main) {
            navigator.toast(error.toMessage())
        }
    }

    private fun handleSuccess(movie: MovieDetail) {
        this.movie = movie
        _movieLD.postValue(MovieDetailModel.Result(movie))
    }

}