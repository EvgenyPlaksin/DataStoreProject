package com.lnight.datastoreproject.preferences_datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PreferencesDataStoreViewModel: ViewModel() {

    private val _preferencesEvent = Channel<PreferencesEvent>()
    val preferencesEvent = _preferencesEvent.receiveAsFlow()

    fun save(key: String, value: String) {
        viewModelScope.launch {
            _preferencesEvent.send(PreferencesEvent.SaveValue(key, value))
        }
    }

    fun get(key: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            _preferencesEvent.send(PreferencesEvent.GetValue(key, onSuccess))
        }
    }

    fun clear() {
        viewModelScope.launch {
            _preferencesEvent.send(PreferencesEvent.ClearPreferences)
        }
    }
}