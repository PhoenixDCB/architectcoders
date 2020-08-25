package com.dacuesta.architectcoders.navigator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dacuesta.architectcoders.MoviesApplication
import com.dacuesta.architectcoders.movies.popularmovies.PopularMoviesFragmentDirections
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class Navigator : KoinComponent {

    private val context by inject<Context>()

    private lateinit var activity: AppCompatActivity

    private val lifecycleCallback = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            // don't implement
        }

        override fun onActivityStarted(activity: Activity) {
            this@Navigator.activity = activity as AppCompatActivity
        }

        override fun onActivityResumed(activity: Activity) {
            // don't implement
        }

        override fun onActivityPaused(activity: Activity) {
            // don't implement
        }

        override fun onActivityStopped(activity: Activity) {
            // don't implement
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // don't implement
        }

        override fun onActivityDestroyed(activity: Activity) {
            // don't implement
        }
    }

    init {
        (context.applicationContext as MoviesApplication)
            .registerActivityLifecycleCallbacks(lifecycleCallback)
    }

    fun toast(res: Int) {
        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
    }

    fun navigate(directions: NavDirections) {
        activity.supportFragmentManager.primaryNavigationFragment?.findNavController()
            ?.navigate(directions)
    }
}