package com.dacuesta.architectcoders.presentation.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.R
import kotlinx.android.synthetic.main.item_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initObservers()
    }

    private fun initObservers() {
        viewModel.movieDetailLiveData.observe(this, Observer { result ->
            result.fold(
                { error ->
                    // TODO: manage error event
                },
                { movieDetail ->
                    title_tv?.text = movieDetail.title
                    release_date_tv?.text = movieDetail.releaseDate
                    overview_tv?.text = movieDetail.overview
                }
            )
        })
    }


}