package com.lnight.datastoreproject.preferences_datastore.simple_exapmle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PreferencesDataStoreViewModel: ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun save(key: String, value: String) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.SaveValue(key, value))
        }
    }

    fun get(key: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.GetValue(key, onSuccess))
        }
    }

    fun clear() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.ClearPreferences)
        }
    }

    fun navigate(route: String) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(route))
        }
    }
}