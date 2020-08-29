package com.dacuesta.architectcoders.data.region

interface RegionDataSource {
    suspend fun get(): String
}