package com.dacuesta.architectcoders.data.region

import org.koin.core.KoinComponent
import org.koin.core.inject

class RegionRepository : KoinComponent {
    private val dataSource by inject<RegionDataSource>()

    suspend fun get() = dataSource.get()
}