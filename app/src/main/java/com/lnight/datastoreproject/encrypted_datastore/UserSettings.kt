package com.lnight.datastoreproject.encrypted_datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val username: String = "",
    val password: String = ""
)