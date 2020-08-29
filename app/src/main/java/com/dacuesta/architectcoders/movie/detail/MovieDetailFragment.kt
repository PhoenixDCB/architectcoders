package com.dacuesta.architectcoders.movie.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.dacuesta.architectcoders.databinding.FragmentMovieDetailBinding
import com.dacuesta.architectcoders.domain.moviedetail.MovieDetail
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.NumberFormat
import java.util.*

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
        initRetryBtn()
    }

    private fun initToolbar() {
        binding.toolbar.title = args.entry.title
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initRetryBtn() {
        binding.contentRetryBtn.setOnClickListener {
            viewModel.retryClicked()
        }
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
        setEmptyStateVisible(false)
        setInformationVisible(false)
    }

    private fun handleMovieResult(model: MovieDetailModel.Result) {
        binding.contentLoaderPb.visibility = View.GONE
        setEmptyStateVisible(model.movie == MovieDetail())
        setInformationVisible(model.movie != MovieDetail())

        binding.appBarImageIv.load(model.movie.backdropImageUrl)
        binding.contentOverviewTv.text = model.movie.overview
        binding.contentReleaseDateTv.text = model.movie.releaseDate
        binding.contentGenresTv.text = TextUtils.join(", ", model.movie.genres)
        binding.contentProductionCompaniesTv.text = TextUtils.join(", ", model.movie.productionCompanies)
        binding.contentBudgetTv.text = model.movie.budget.toCurrency()
        binding.contentRevenueTv.text = model.movie.revenue.toCurrency()
        binding.contentSpokenLanguagesTv.text = TextUtils.join(", ", model.movie.spokenLanguages)
    }

    private fun setEmptyStateVisible(isVisible: Boolean) {
        val visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.contentEmptyStateTv.visibility = visibility
        binding.contentRetryBtn.visibility = visibility
    }

    private fun setInformationVisible(isVisible: Boolean) {
        val visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.contentOverviewTitleTv.visibility = visibility
        binding.contentOverviewTv.visibility = visibility

        binding.contentReleaseDateTitleTv.visibility = visibility
        binding.contentReleaseDateTv.visibility = visibility

        binding.contentGenresTitleTv.visibility = visibility
        binding.contentGenresTv.visibility = visibility

        binding.contentProductionCompaniesTitleTv.visibility = visibility
        binding.contentProductionCompaniesTv.visibility = visibility

        binding.contentBudgetTitleTv.visibility = visibility
        binding.contentBudgetTv.visibility = visibility

        binding.contentRevenueTitleTv.visibility = visibility
        binding.contentRevenueTv.visibility = visibility

        binding.contentSpokenLanguagesTitleTv.visibility = visibility
        binding.contentSpokenLanguagesTv.visibility = visibility
    }

    private fun Long.toCurrency() : String {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 0
        return format.format(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}