package com.lnight.datastoreproject

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import com.lnight.datastoreproject.proto_datastore.AppSettingsSerializer

private const val DATASTORE_PREFERENCES_NAME = "preferences_datastore"
private const val DATASTORE_PROTO_NAME = "proto_datastore.json"

val Context.preferencesDataStore by preferencesDataStore(
    name = DATASTORE_PREFERENCES_NAME
)

val Context.protoDataStore by dataStore(
    fileName = DATASTORE_PROTO_NAME,
    serializer = AppSettingsSerializer
)