package com.dacuesta.architectcoders.movies.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.scope.viewModel

class PopularMoviesFragment : Fragment() {
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding!!

    private val viewModel by currentScope.viewModel<PopularMoviesViewModel>(this)

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
        initToolbar()
        initMoviesSwipeLayout()
        initMoviesRv()
    }

    private fun initToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initMoviesSwipeLayout() {
        binding.moviesSwipeLayout.setOnRefreshListener {
            viewModel.refreshClicked()
        }
    }

    private fun initMoviesRv() {
        moviesAdapter = PopularMoviesAdapter(
            favoriteMoviesLD = viewModel.favoriteMoviesLD,
            imageClicked = viewModel::imageClicked,
            favoriteClicked = viewModel::favoriteClicked
        )
        binding.moviesRv.setHasFixedSize(true)
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun initObservers() {
        viewModel.loaderLD.observe(viewLifecycleOwner, Observer(::handleLoader))
        viewModel.popularMoviesLD.observe(viewLifecycleOwner, Observer(::handlePopularMovies))
    }

    private fun handleLoader(model: PopularMoviesModel.Loader) {
        binding.moviesSwipeLayout.isRefreshing = model.isVisible
    }

    private fun handlePopularMovies(model: PopularMoviesModel.PopularMovies) {
        if (model.movies.isEmpty()) {
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.emptyStateTv.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
        moviesAdapter.submitList(model.movies)
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}