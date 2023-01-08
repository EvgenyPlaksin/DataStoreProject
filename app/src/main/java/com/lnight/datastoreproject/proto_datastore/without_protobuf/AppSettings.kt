package com.lnight.datastoreproject.proto_datastore.without_protobuf

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