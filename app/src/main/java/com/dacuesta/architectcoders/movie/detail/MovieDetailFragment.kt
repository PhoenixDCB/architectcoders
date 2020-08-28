package com.dacuesta.architectcoders.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.dacuesta.architectcoders.databinding.FragmentMovieDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding!!

    private val args by navArgs<MovieDetailFragmentArgs>()

    private val viewModel by viewModel<MovieDetailViewModel> {
        parametersOf(args.entry)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieDetailBinding.inflate(inflater, container, false).run {
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
    }

    private fun initToolbar() {
        binding.toolbar.title = args.entry.title
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initObservers() {
        viewModel.movieLD.observe(viewLifecycleOwner, ::handleMovie)
    }

    private fun handleMovie(model: MovieDetailModel) {
        when (model) {
            is MovieDetailModel.Loader -> handleMovieLoader()
            is MovieDetailModel.Result -> handleMovieResult(model)
        }
    }

    private fun handleMovieLoader() {
        binding.contentLoaderPb.visibility = View.VISIBLE
    }

    private fun handleMovieResult(model: MovieDetailModel.Result) {
        binding.contentLoaderPb.visibility = View.GONE

        binding.appBarImageIv.load(model.movie.backdropImageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}