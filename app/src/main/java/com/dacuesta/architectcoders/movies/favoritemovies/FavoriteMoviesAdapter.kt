package com.dacuesta.architectcoders.movies.favoritemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dacuesta.architectcoders.databinding.ItemFavoriteMoviesMovieBinding
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.movies.favoritemovies.FavoriteMoviesAdapter.MovieVH

class FavoriteMoviesAdapter(
    private val imageClicked: (Movie) -> Unit,
    private val favoriteClicked: (Movie) -> Unit
) : ListAdapter<Movie, MovieVH>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ) =
                oldItem == newItem
        }
    }

    inner class MovieVH(
        private val binding: ItemFavoriteMoviesMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.imageIv.load(movie.backdropImageUrl)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieVH(
            ItemFavoriteMoviesMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }
}