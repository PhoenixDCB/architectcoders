package com.dacuesta.architectcoders.presentation.main.popularmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
import com.dacuesta.architectcoders.domain.common.model.movies.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding!!

    private val viewModel by viewModel<PopularMoviesViewModel>()

    private lateinit var moviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPopularMoviesBinding.inflate(inflater, container, false).run {
        _binding = this
        root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() {
        moviesAdapter = PopularMoviesAdapter(imageClicked = ::imageClicked, favoriteClicked = ::favoriteClicked)
        binding.moviesRv.setHasFixedSize(true)
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun imageClicked(movie: Movie) {
        TODO("Not yet implemented")
    }

    private fun favoriteClicked(movie: Movie) {
        TODO("Not yet implemented")
    }

    private fun initObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer(::handleMovies))
    }

    private fun handleMovies(movies: List<Movie>) {
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