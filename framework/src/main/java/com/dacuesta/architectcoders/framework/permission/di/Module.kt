package com.dacuesta.architectcoders.framework.permission.di

import com.dacuesta.architectcoders.framework.permission.AppPermission
import org.koin.dsl.module

val appPermissionModule = module {
    single {
        AppPermission()
    }
}