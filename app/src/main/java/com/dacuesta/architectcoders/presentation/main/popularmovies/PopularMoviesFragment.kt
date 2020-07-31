package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
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

    private fun handleMovies(movies: List<MovieEntity>) {
        moviesAdapter.submitList(movies)

        if (movies.isEmpty()) {
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.emptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        binding.loaderPb.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}