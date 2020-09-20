package com.dacuesta.architectcoders.navigator

import androidx.navigation.NavDirections

class FakeNavigator : Navigator {
    override fun toast(res: Int) {
        // do nothing
    }

    override fun navigate(directions: NavDirections) {
        // do nothing
    }

}