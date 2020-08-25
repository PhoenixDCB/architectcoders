package com.dacuesta.architectcoders.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavArgument
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private val args by navArgs<MovieActivityArgs>()

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navGraph =
            navHostFragment.navController.navInflater.inflate(R.navigation.movie_nav_graph)
        val navArgument = NavArgument.Builder().setDefaultValue(args.id).build()
        navGraph.addArgument("id", navArgument)
        navHostFragment.navController.graph = navGraph
    }
}