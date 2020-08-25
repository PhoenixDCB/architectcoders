package com.dacuesta.architectcoders.movies.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentFavoriteMoviesBinding
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
        viewModel.favoriteMoviesLD.observe(viewLifecycleOwner, Observer(::handleFavoriteMovies))
    }

    private fun handleFavoriteMovies(model: FavoriteMoviesModel.FavoriteMovies) {
        if (model.movies.isEmpty()) {
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.emptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        moviesAdapter.submitList(model.movies)
    }

}