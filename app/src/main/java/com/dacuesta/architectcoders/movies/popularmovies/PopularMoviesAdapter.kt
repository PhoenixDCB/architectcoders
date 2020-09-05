package com.dacuesta.architectcoders.movies.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.ItemPopularMoviesMovieBinding
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesAdapter.MovieVH

class PopularMoviesAdapter(
    private val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>,
    private val imageClicked: (Movie) -> Unit,
    private val favoriteClicked: (Movie, Boolean) -> Unit
) : ListAdapter<Movie, MovieVH>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }

    inner class MovieVH(
        private val binding: ItemPopularMoviesMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var isFavorite: Boolean = false
            set(value) {
                field = value
                binding.favoriteIv.setImageResource(
                    if (value) {
                        R.drawable.ic_favorite_on
                    } else {
                        R.drawable.ic_favorite_off
                    }
                )
            }

        fun bind(movie: Movie) {
            binding.imageIv.load(movie.posterImageUrl)
            binding.titleTv.text = movie.title
            binding.releaseDateTv.text = movie.releaseDate

            favoriteMoviesLD.observe(
                (binding.root.context as LifecycleOwner),
                { model ->
                    isFavorite = model.movies.contains(movie)
                }
            )

            binding.imageIv.setOnClickListener {
                imageClicked(movie)
            }

            binding.favoriteIv.setOnClickListener {
                isFavorite = !isFavorite
                favoriteClicked(movie, !isFavorite)
            }
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieVH(
            binding = ItemPopularMoviesMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }
}