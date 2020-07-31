package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.ItemPopularMovieBinding
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.presentation.main.popularmovies.PopularMoviesAdapter.MovieVH
import kotlinx.coroutines.flow.collect

class PopularMoviesAdapter(
    private val favoriteMoviesLD: LiveData<List<MovieEntity>>,
    private val imageClicked: (MovieEntity) -> Unit,
    private val favoriteClicked: (MovieEntity, Boolean) -> Unit
) : ListAdapter<MovieEntity, MovieVH>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MovieVH(
        private val binding: ItemPopularMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var isFavorite: Boolean = false
            set(value) {
                field = value
                binding.favoriteIv.setImageResource(
                    if (value) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_favorite_border
                    }
                )
            }

        fun bind(movie: MovieEntity) {
            binding.imageIv.load(movie.imageUrl)
            binding.titleTv.text = movie.title
            binding.releaseDateTv.text = movie.releaseDate

            favoriteMoviesLD.observe(
                (binding.root.context as LifecycleOwner),
                Observer { movies ->
                    isFavorite = movies.contains(movie)
                }
            )

            binding.imageIv.setOnClickListener {
                imageClicked(movie)
            }

            binding.favoriteIv.setOnClickListener {
                favoriteClicked(movie, isFavorite)
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