package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
import com.dacuesta.architectcoders.domain.MoviesState
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

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
            imageClicked = ::imageClicked,
            favoriteClicked = ::favoriteClicked,
            loadMore = ::loadMore
        )
        binding.moviesRv.setHasFixedSize(true)
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun imageClicked(movie: MovieEntity) {
        TODO("Not yet implemented")
    }

    @ExperimentalCoroutinesApi
    private fun favoriteClicked(movie: MovieEntity, isFavorite: Boolean) {
        viewModel.favoriteClicked(movie, isFavorite)
    }

    @ExperimentalCoroutinesApi
    private fun loadMore() {
        viewModel.loadMore()
    }

    @ExperimentalCoroutinesApi
    private fun initObservers() {
        viewModel.popularMoviesLD.observe(viewLifecycleOwner, Observer(::handleMovies))
    }

    private fun handleMovies(state: MoviesState<List<MovieEntity>>) {
        when (state) {
            is MoviesState.Loading -> handleLoading()
            is MoviesState.Success -> handleSuccess(state.value)
            is MoviesState.Failure -> handleFailure()
        }
    }

    private fun handleLoading() {
        binding.emptyStateTv.visibility = View.GONE
        if (moviesAdapter.itemCount <= 0) {
            binding.loaderPb.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.loaderPb.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
    }

    private fun handleSuccess(movies: List<MovieEntity>) {
        binding.loaderPb.visibility = View.GONE
        if (movies.isEmpty()) {
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.emptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        moviesAdapter.submitList(movies)
    }

    private fun handleFailure() {
        binding.loaderPb.visibility = View.GONE
        if (moviesAdapter.itemCount <= 0) {
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.emptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        showError()
    }

    private fun showError() {
        Toast.makeText(requireContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}