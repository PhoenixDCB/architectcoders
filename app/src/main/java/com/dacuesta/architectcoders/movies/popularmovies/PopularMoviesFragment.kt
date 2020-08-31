package com.dacuesta.architectcoders.movies.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dacuesta.architectcoders.databinding.FragmentPopularMoviesBinding
import com.dacuesta.architectcoders.movies.popularmovies.adapter.PopularMoviesAdapter
import com.dacuesta.architectcoders.movies.popularmovies.adapter.PopularMoviesItem
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
        initToolbar()
        initMoviesSwipeLayout()
        initMoviesRv()
        initRetryBtn()
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
            endReached = viewModel::endReached,
            imageClicked = viewModel::imageClicked,
            favoriteClicked = viewModel::favoriteClicked
        )
        binding.moviesRv.setHasFixedSize(true)
        (binding.moviesRv.layoutManager as GridLayoutManager).let { layoutManager ->
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    if (moviesAdapter.getItemViewType(position) == PopularMoviesAdapter.TYPE_LOADER) {
                        layoutManager.spanCount
                    } else {
                        1
                    }
            }
        }
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun initRetryBtn() {
        binding.retryBtn.setOnClickListener {
            viewModel.retryClicked()
        }
    }

    private fun initObservers() {
        viewModel.popularMoviesLD.observe(viewLifecycleOwner, Observer(::handlePopularMovies))
    }

    private fun handlePopularMovies(model: PopularMoviesModel.PopularMovies) {
        when (model) {
            is PopularMoviesModel.PopularMovies.Loader -> handlePopularMoviesLoader(model)
            is PopularMoviesModel.PopularMovies.Result -> handlePopularMoviesResult(model)
            is PopularMoviesModel.PopularMovies.HideRefreshLoader -> handlePopularMoviesHideRefreshLoader()
        }
    }

    private fun handlePopularMoviesLoader(model: PopularMoviesModel.PopularMovies.Loader) {
        if (model.movies.isEmpty()) {
            binding.loaderPb.visibility = View.VISIBLE
            binding.emptyStateTv.visibility = View.GONE
            binding.retryBtn.visibility = View.GONE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.loaderPb.visibility = View.GONE
            binding.emptyStateTv.visibility = View.GONE
            binding.retryBtn.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE

            val items = mutableListOf<PopularMoviesItem>()
            model.movies.forEach { movie ->
                items.add(PopularMoviesItem.Result(movie))
            }
            items.add(PopularMoviesItem.Loader)
            moviesAdapter.submitList(items)
        }
    }

    private fun handlePopularMoviesResult(model: PopularMoviesModel.PopularMovies.Result) {
        if (model.movies.isEmpty()) {
            binding.loaderPb.visibility = View.GONE
            binding.emptyStateTv.visibility = View.VISIBLE
            binding.retryBtn.visibility = View.VISIBLE
            binding.moviesRv.visibility = View.GONE
        } else {
            binding.loaderPb.visibility = View.GONE
            binding.emptyStateTv.visibility = View.GONE
            binding.retryBtn.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }

        binding.moviesSwipeLayout.isRefreshing = false

        val items = mutableListOf<PopularMoviesItem>()
        model.movies.forEach { movie ->
            items.add(PopularMoviesItem.Result(movie))
        }
        moviesAdapter.submitList(items)
    }

    private fun handlePopularMoviesHideRefreshLoader() {
        binding.moviesSwipeLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}