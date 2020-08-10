package com.dacuesta.architectcoders.presentation.main.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentFavoriteMoviesBinding
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding: FragmentFavoriteMoviesBinding
        get() = _binding!!

    private val viewModel by viewModel<FavoriteMoviesViewModel>()

    private lateinit var moviesAdapter: FavoriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFavoriteMoviesBinding.inflate(inflater, container, false).run {
        _binding = this
        root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() {
        moviesAdapter = FavoriteMoviesAdapter(
            imageClicked = viewModel::imageClicked,
            favoriteClicked = viewModel::favoriteClicked
        )
        binding.moviesRv.setHasFixedSize(true)
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun initObservers() {
        viewModel.moviesLD.observe(viewLifecycleOwner, Observer(::handleMovies))
    }

    private fun handleMovies(movies: List<MovieEntity>) {
        if (movies.isEmpty()) {
            binding.moviesEmptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.moviesEmptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        moviesAdapter.submitList(movies)
    }

}