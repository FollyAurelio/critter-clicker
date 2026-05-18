package com.example.critter_clicker.data.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.critter_clicker.data.settings.model.SettingsState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        SettingsRepository(application)

    val settingsState: StateFlow<SettingsState> =
        repository.settingsFlow.stateIn(
            scope = viewModelScope,
            started =
                SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = SettingsState(0)
        )

    fun updateCookies(newCookies: Long) {

        viewModelScope.launch {
            repository.updateCookies(newCookies)
        }
    }
}