package com.dacuesta.architectcoders.movies.popularmovies.adapter

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
import com.dacuesta.architectcoders.databinding.ItemPopularMoviesLoaderBinding
import com.dacuesta.architectcoders.databinding.ItemPopularMoviesMovieBinding
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesModel
import com.dacuesta.architectcoders.movies.popularmovies.adapter.PopularMoviesAdapter.BaseVH

class PopularMoviesAdapter(
    private val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>,
    private val endReached: () -> Unit,
    private val imageClicked: (Movie) -> Unit,
    private val favoriteClicked: (Movie, Boolean) -> Unit
) : ListAdapter<PopularMoviesItem, BaseVH<PopularMoviesItem>>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularMoviesItem>() {
            override fun areItemsTheSame(
                oldItem: PopularMoviesItem,
                newItem: PopularMoviesItem
            ) =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PopularMoviesItem,
                newItem: PopularMoviesItem
            ) =
                oldItem == newItem
        }

        const val TYPE_MOVIE = 0
        const val TYPE_LOADER = 1
    }

    abstract class BaseVH<T : PopularMoviesItem>(
        binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    inner class MovieVH(
        private val binding: ItemPopularMoviesMovieBinding
    ) : BaseVH<PopularMoviesItem.Result>(binding) {

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

        override fun bind(item: PopularMoviesItem.Result) {
            binding.imageIv.load(item.movie.posterImageUrl)
            binding.titleTv.text = item.movie.title
            binding.releaseDateTv.text = item.movie.releaseDate

            favoriteMoviesLD.observe(
                (binding.root.context as LifecycleOwner),
                { model ->
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

    class LoaderVH(
        binding: ItemPopularMoviesLoaderBinding
    ) : BaseVH<PopularMoviesItem.Loader>(binding) {

        override fun bind(item: PopularMoviesItem.Loader) {
            // don't implement
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            TYPE_MOVIE -> MovieVH(
                ItemPopularMoviesMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ) as BaseVH<PopularMoviesItem>
            else -> LoaderVH(
                ItemPopularMoviesLoaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ) as BaseVH<PopularMoviesItem>
        }

    override fun onBindViewHolder(holder: BaseVH<PopularMoviesItem>, position: Int) {
        if (position == itemCount - 1) {
            endReached()
        }

        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is PopularMoviesItem.Result -> TYPE_MOVIE
        is PopularMoviesItem.Loader -> TYPE_LOADER
    }
}