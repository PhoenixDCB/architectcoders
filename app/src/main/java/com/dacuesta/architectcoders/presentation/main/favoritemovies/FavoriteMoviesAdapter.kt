package com.dacuesta.architectcoders.presentation.main.favoritemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dacuesta.architectcoders.databinding.ItemFavoriteMovieBinding
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.presentation.main.favoritemovies.FavoriteMoviesAdapter.MovieVH

class FavoriteMoviesAdapter(
    private val imageClicked: (MovieEntity) -> Unit,
    private val favoriteClicked: (MovieEntity) -> Unit
) : ListAdapter<MovieEntity, MovieVH>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ) =
                oldItem == newItem
        }
    }

    inner class MovieVH(
        private val binding: ItemFavoriteMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
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
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }
}