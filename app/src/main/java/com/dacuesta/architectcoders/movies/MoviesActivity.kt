package com.dacuesta.architectcoders.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dacuesta.architectcoders.R
import com.dacuesta.architectcoders.databinding.ActivityMoviesBinding
import org.koin.android.ext.android.getKoin
import org.koin.ext.getOrCreateScope
import org.koin.ext.getScopeId

class MoviesActivity : AppCompatActivity() {
    companion object {
        lateinit var SCOPE_ID: String
    }

    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDi()
        initNavigation()
    }

    private fun initDi() {
        SCOPE_ID = getScopeId()
        getOrCreateScope()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigation, navHostFragment.navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().deleteScope(this.javaClass.simpleName)
    }
}