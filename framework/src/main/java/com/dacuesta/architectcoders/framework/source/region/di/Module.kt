package com.dacuesta.architectcoders.framework.source.region.di

import com.dacuesta.architectcoders.data.region.RegionDataSource
import com.dacuesta.architectcoders.framework.source.region.RegionDataSourceImpl
import org.koin.dsl.module

val regionDataSourceModule = module {
    single<RegionDataSource> {
        RegionDataSourceImpl()
    }
}