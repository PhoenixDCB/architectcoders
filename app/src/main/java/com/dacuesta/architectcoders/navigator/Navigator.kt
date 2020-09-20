package com.dacuesta.architectcoders.navigator

import androidx.navigation.NavDirections

interface Navigator {
    fun toast(res: Int)
    fun navigate(directions: NavDirections)
}