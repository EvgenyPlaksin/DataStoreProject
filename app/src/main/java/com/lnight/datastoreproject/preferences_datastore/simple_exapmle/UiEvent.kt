package com.lnight.datastoreproject.preferences_datastore.simple_exapmle

sealed interface UiEvent {
    class SaveValue(val key: String, val value: String): UiEvent
    class GetValue(val key: String, val OnSuccess: (String) -> Unit): UiEvent
    object ClearPreferences : UiEvent
    class Navigate(val route: String): UiEvent
}