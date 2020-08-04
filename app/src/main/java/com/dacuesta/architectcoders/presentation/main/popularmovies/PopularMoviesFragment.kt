package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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
        binding.loaderPb.visibility = View.VISIBLE
        binding.moviesEmptyStateTv.visibility = View.GONE
        binding.retryBtn.visibility = View.GONE
        binding.moviesRv.visibility = View.GONE
    }

    private fun handlePopularMoviesResult(model: PopularMoviesModel.PopularMovies.Result) {
        binding.loaderPb.visibility = View.GONE
        if (model.movies.isEmpty()) {
            binding.moviesEmptyStateTv.visibility = View.VISIBLE
            binding.retryBtn.visibility = View.VISIBLE
        } else {
            binding.moviesRv.visibility = View.VISIBLE
        }

        moviesAdapter.submitList(model.movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}