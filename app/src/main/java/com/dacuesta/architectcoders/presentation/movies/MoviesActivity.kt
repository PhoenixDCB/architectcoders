package com.dacuesta.architectcoders.presentation.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.domain.movies.model.Movie
import com.dacuesta.architectcoders.presentation.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val viewModel by viewModel<MoviesViewModel>()

    private val moviesAdapter = MoviesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initViews()
        initObservers()
    }

    private fun initViews() {
        movies_rv?.setHasFixedSize(true)
        movies_rv?.adapter = moviesAdapter
    }

    private fun initObservers() {
        viewModel.moviesLiveData.observe(this, Observer { result ->
            result.fold(
                { error ->
                    // TODO: manage error event
                },
                { movies ->
                    moviesAdapter.submitList(movies)
                }
            )
        })
    }
}

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.title_tv?.text = movie.title
            itemView.release_date_tv?.text = movie.releaseDate
            itemView.overview_tv?.text = movie.overview

            itemView.setOnClickListener { view ->
                view.context.startActivity(
                    Intent(view.context, MovieDetailActivity::class.java)
                )
            }
        }
    }

}