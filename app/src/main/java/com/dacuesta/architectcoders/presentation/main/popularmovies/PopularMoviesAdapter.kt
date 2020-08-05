package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.api.load
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.ItemLoaderPopularMovieBinding
import com.dacuesta.architectcoders.databinding.ItemMoviePopularMovieBinding
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.presentation.main.popularmovies.PopularMoviesAdapter.BaseVH

class PopularMoviesAdapter(
    private val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>,
    private val endReached: () -> Unit,
    private val imageClicked: (MovieEntity) -> Unit,
    private val favoriteClicked: (MovieEntity, Boolean) -> Unit
) : ListAdapter<PopularMoviesItem, BaseVH<PopularMoviesItem>>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularMoviesItem>() {
            override fun areItemsTheSame(
                oldItem: PopularMoviesItem,
                newItem: PopularMoviesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PopularMoviesItem,
                newItem: PopularMoviesItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        const val TYPE_MOVIE = 0
        const val TYPE_LOADER = 1
    }

    abstract inner class BaseVH<T : PopularMoviesItem>(
        binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    inner class MovieVH(
        private val binding: ItemMoviePopularMovieBinding
    ) : BaseVH<PopularMoviesItem.Movie>(binding) {

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

        override fun bind(item: PopularMoviesItem.Movie) {
            binding.imageIv.load(item.movie.imageUrl)
            binding.titleTv.text = item.movie.title
            binding.releaseDateTv.text = item.movie.releaseDate

            favoriteMoviesLD.observe(
                (binding.root.context as LifecycleOwner),
                Observer { model ->
                    isFavorite = model.movies.contains(item.movie)
                }
            )

            binding.imageIv.setOnClickListener {
                imageClicked(item.movie)
            }

            binding.favoriteIv.setOnClickListener {
                isFavorite = !isFavorite
                favoriteClicked(item.movie, !isFavorite)
            }
        }

    }

    inner class LoaderVH(
        binding: ItemLoaderPopularMovieBinding
    ) : BaseVH<PopularMoviesItem.Loader>(binding) {

        override fun bind(item: PopularMoviesItem.Loader) {
            // don't implement
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_MOVIE -> MovieVH(
            ItemMoviePopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) as BaseVH<PopularMoviesItem>
        else -> LoaderVH(
            ItemLoaderPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) as BaseVH<PopularMoviesItem>
    }

    override fun onBindViewHolder(holder: BaseVH<PopularMoviesItem>, position: Int) {
        if (position == itemCount - 1) {
            endReached()
        }

        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is PopularMoviesItem.Movie -> TYPE_MOVIE
        is PopularMoviesItem.Loader -> TYPE_LOADER
    }
}