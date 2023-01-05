package com.lnight.datastoreproject.preferences_datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_PREFERENCES_NAME = "preferences_datastore"

val Context.dataStore by preferencesDataStore(
    name = DATASTORE_PREFERENCES_NAME
)