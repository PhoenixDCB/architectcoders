package com.dacuesta.architectcoders.domain.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Error : Parcelable {
    @Parcelize
    object Empty : Error()

    @Parcelize
    object Server : Error()

    @Parcelize
    object Unknown : Error()
}