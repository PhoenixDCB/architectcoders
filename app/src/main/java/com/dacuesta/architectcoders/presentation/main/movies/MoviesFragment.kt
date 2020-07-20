package com.dacuesta.architectcoders.presentation.main.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentMoviesBinding
import com.dacuesta.architectcoders.domain.common.model.movies.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding
        get() = _binding!!

    private val viewModel by viewModel<MoviesViewModel>()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMoviesBinding.inflate(inflater, container, false).run {
        _binding = this
        root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() {
        moviesAdapter = MoviesAdapter()
        binding.moviesRv.setHasFixedSize(true)
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun initObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer(::handleMovies))
    }

    private fun handleMovies(movies: List<Movie>) {
        moviesAdapter.submitList(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}