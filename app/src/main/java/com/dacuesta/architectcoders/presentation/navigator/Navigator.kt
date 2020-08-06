package com.dacuesta.architectcoders.presentation.navigator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dacuesta.architectcoders.MoviesApplication
import org.koin.core.KoinComponent
import org.koin.core.inject

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

    fun showToast(res: Int) =
        Toast.makeText(activity, res, Toast.LENGTH_SHORT).show()
}