package com.lnight.datastoreproject.preferences_datastore.ecrypted_preferences

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val username: String = "",
    val password: String = ""
)