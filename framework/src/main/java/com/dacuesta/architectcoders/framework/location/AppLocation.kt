package com.dacuesta.architectcoders.framework.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.dacuesta.architectcoders.framework.permission.AppPermission
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

internal class AppLocation(
    private val context: Context,
    private val appPermission: AppPermission
) {

    @SuppressLint("MissingPermission")
    @Suppress("EXPERIMENTAL_API_USAGE")
    suspend fun getLastLocation(rationaleMessage: String): Location? =
        suspendCancellableCoroutine { cancellableContinuation ->
            fun handleLocation() {
                val client = LocationServices.getFusedLocationProviderClient(context)
                client.lastLocation.addOnSuccessListener { location: Location? ->
                    cancellableContinuation.resume(location)
                }.addOnFailureListener {
                    cancellableContinuation.resume(null)
                }.addOnCanceledListener {
                    cancellableContinuation.resume(null)
                }
            }

            appPermission.request(
                permissions = listOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                rationaleMessage = rationaleMessage,
                onDenied = ::handleLocation,
                onGranted = ::handleLocation
            )
        }
    
}