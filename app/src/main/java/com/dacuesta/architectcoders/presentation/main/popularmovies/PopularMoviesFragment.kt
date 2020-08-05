package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding!!

    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<PopularMoviesViewModel>()

    private lateinit var moviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPopularMoviesBinding.inflate(inflater, container, false).run {
        _binding = this
        root
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
    }

    @ExperimentalCoroutinesApi
    private fun initViews() {
        moviesAdapter = PopularMoviesAdapter(
            favoriteMoviesLD = viewModel.favoriteMoviesLD,
            endReached = viewModel::endReached,
            imageClicked = viewModel::imageClicked,
            favoriteClicked = viewModel::favoriteClicked
        )
        binding.moviesRv.setHasFixedSize(true)
        (binding.moviesRv.layoutManager as GridLayoutManager).let { layoutManager ->
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    if (position == layoutManager.itemCount - 1) {
                        2
                    } else {
                        1
                    }
            }
        }
        binding.moviesRv.adapter = moviesAdapter

        binding.retryBtn.setOnClickListener {
            viewModel.retryClicked()
        }
    }

    @ExperimentalCoroutinesApi
    private fun initObservers() {
        viewModel.popularMoviesLD.observe(viewLifecycleOwner, Observer(::handlePopularMovies))
    }

    private fun handlePopularMovies(model: PopularMoviesModel.PopularMovies) {
        when (model) {
            is PopularMoviesModel.PopularMovies.Loading -> handlePopularMoviesLoading()
            is PopularMoviesModel.PopularMovies.Result -> handlePopularMoviesResult(model)
        }
    }

    private fun handlePopularMoviesLoading() {
        if (moviesAdapter.itemCount <= 0) {
            binding.loaderPb.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
            binding.moviesEmptyStateTv.visibility = View.GONE
            binding.retryBtn.visibility = View.GONE
        }
    }

    private fun handlePopularMoviesResult(model: PopularMoviesModel.PopularMovies.Result) {
        binding.loaderPb.visibility = View.GONE
        if (model.movies.isEmpty()) {
            binding.moviesEmptyStateTv.visibility = View.VISIBLE
            binding.retryBtn.visibility = View.VISIBLE
        } else {
            binding.moviesRv.visibility = View.VISIBLE
        }

        val items = mutableListOf<PopularMoviesItem>()
        model.movies.forEach { movie ->
            items.add(PopularMoviesItem.Movie(movie))
        }
        items.add(PopularMoviesItem.Loader)
        moviesAdapter.submitList(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}