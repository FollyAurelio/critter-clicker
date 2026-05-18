package com.example.critter_clicker.data.inventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.critter_clicker.data.inventory.model.InventoryState
import com.example.critter_clicker.data.settings.model.SettingsState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        InventoryRepository(application)

    val inventoryState: StateFlow<InventoryState> =
        repository.inventoryFlow.stateIn(
            scope = viewModelScope,
            started =
                SharingStarted.Companion.WhileSubscribed(5000),
            initialValue = InventoryState(
                0,
                1
            )
        )

    fun updateCookies(newCookies: Long) {

        viewModelScope.launch {
            repository.updateCookies(newCookies)
        }
    }
}