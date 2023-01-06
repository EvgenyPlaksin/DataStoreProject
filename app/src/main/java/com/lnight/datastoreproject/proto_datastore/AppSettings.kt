package com.lnight.datastoreproject.proto_datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: Language = Language.ENGLISH,
    val knownLocations: List<Location> = emptyList()
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

enum class Language {
    ENGLISH, GERMAN, SPANISH
}