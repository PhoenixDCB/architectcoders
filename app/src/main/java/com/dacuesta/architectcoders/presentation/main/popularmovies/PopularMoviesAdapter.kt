package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dacuesta.architectcoders.databinding.ItemPopularMovieBinding
import com.dacuesta.architectcoders.domain.common.model.movies.Movie
import com.dacuesta.architectcoders.presentation.main.popularmovies.PopularMoviesAdapter.MovieVH

class PopularMoviesAdapter(
    private val imageClicked: (Movie) -> Unit,
    private val favoriteClicked: (Movie) -> Unit
) : ListAdapter<Movie, MovieVH>(DIFF_CALLBACK) {

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

    inner class MovieVH(
        private val binding: ItemPopularMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.imageIv.load(movie.imageUrl)
            binding.titleTv.text = movie.title
            binding.releaseDateTv.text = movie.releaseDate

            binding.imageIv.setOnClickListener {
                imageClicked(movie)
            }

            binding.favoriteIv.setOnClickListener {
                favoriteClicked(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieVH(
        ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }

}