package com.dacuesta.architectcoders

import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.navigator.NavigatorImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("io")) { Dispatchers.IO }
    single<CoroutineDispatcher>(named("main")) { Dispatchers.Main }

    single<Navigator> { NavigatorImpl(context = androidContext()) }
}