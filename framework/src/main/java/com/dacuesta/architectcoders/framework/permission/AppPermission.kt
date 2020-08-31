package com.dacuesta.architectcoders.framework.permission

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.dacuesta.architectcoders.framework.R
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener
import org.koin.core.KoinComponent
import org.koin.core.inject


class AppPermission : KoinComponent {

    private val context by inject<Context>()

    private lateinit var activity: Activity

    private val lifecycleCallback = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            activity = p0
        }

        override fun onActivityStarted(p0: Activity) {
            activity = p0
        }

        override fun onActivityResumed(p0: Activity) {
            // Don't handle this event
        }

        override fun onActivityPaused(p0: Activity) {
            // Don't handle this event
        }

        override fun onActivityStopped(p0: Activity) {
            // Don't handle this event
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            // Don't handle this event
        }

        override fun onActivityDestroyed(p0: Activity) {
            // Don't handle this event
        }

    }

    init {
        (context as Application).registerActivityLifecycleCallbacks(lifecycleCallback)
    }

    fun request(
        permissions: List<String>,
        rationaleMessage: String,
        onDenied: () -> Unit,
        onGranted: () -> Unit
    ) {
        val view = activity.findViewById<View>(android.R.id.content).rootView
        val rationaleListener = SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
            .with(view, rationaleMessage)
            .withOpenSettingsButton(R.string.permission_settings)
            .withDuration(Snackbar.LENGTH_LONG)
            .build()
        val checkedPermissionListener = object : BaseMultiplePermissionsListener() {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport) {
                super.onPermissionsChecked(p0)
                if (p0.areAllPermissionsGranted()) {
                    onGranted()
                } else {
                    onDenied()
                }
            }
        }
        val listener = CompositeMultiplePermissionsListener(
            rationaleListener,
            checkedPermissionListener
        )

        Dexter.withContext(context)
            .withPermissions(permissions)
            .withListener(listener)
            .check()
    }

}