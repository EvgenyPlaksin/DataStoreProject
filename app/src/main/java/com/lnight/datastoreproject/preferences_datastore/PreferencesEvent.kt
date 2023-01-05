package com.lnight.datastoreproject.preferences_datastore

sealed interface PreferencesEvent {
    class SaveValue(val key: String, val value: String): PreferencesEvent
    class GetValue(val key: String, val OnSuccess: (String) -> Unit): PreferencesEvent
    object ClearPreferences : PreferencesEvent
}