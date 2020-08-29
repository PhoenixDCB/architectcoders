package com.dacuesta.architectcoders.data.region.di

import com.dacuesta.architectcoders.data.region.RegionRepository
import org.koin.dsl.module

val regionRepositoryModule = module {
    single {
        RegionRepository()
    }
}