package com.example.critter_clicker.data.inventory

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.critter_clicker.data.inventory.model.InventoryState
import com.example.critter_clicker.data.settings.model.SettingsState
import kotlinx.coroutines.flow.map

class InventoryRepository(private val context : Context){

    val inventoryFlow = context.dataStore.data.map { prefs ->
        InventoryState(
            prefs[Keys.TOTAL_COOKIES] ?: 0L,
            prefs[Keys.COOKIES_PER_CLICK] ?: 1L,
        )
    }

    suspend fun updateCookies(newCookies : Long){
        context.dataStore.edit { prefs ->

            prefs[Keys.TOTAL_COOKIES] = newCookies
        }

    }
}