package com.dacuesta.architectcoders.framework.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import kotlin.coroutines.resume

class AppLocation : KoinComponent {
    private val context by inject<Context>()

    @SuppressLint("MissingPermission")
    @Suppress("EXPERIMENTAL_API_USAGE")
    suspend fun getLastLocation(): Location? =
        suspendCancellableCoroutine { cancellableContinuation ->
            val client = LocationServices.getFusedLocationProviderClient(context)
            client.lastLocation.addOnSuccessListener { location: Location? ->
                Timber.d("location = $location")
                cancellableContinuation.resume(location)
            }.addOnFailureListener {
                Timber.d("location = [failure]")
                cancellableContinuation.resume(null)
            }.addOnCanceledListener {
                Timber.d("location = [canceled]")
                cancellableContinuation.resume(null)
            }
        }
}