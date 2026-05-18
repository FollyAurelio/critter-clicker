package com.example.critter_clicker.data.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.critter_clicker.data.settings.model.SettingsState
import kotlinx.coroutines.flow.map

class SettingsRepository(private val context : Context){

    val settingsFlow = context.dataStore.data.map { prefs ->
        SettingsState(
            prefs[Keys.TOTAL_COOKIES] ?: 0L,
        )
    }

    suspend fun updateCookies(newCookies : Long){
        context.dataStore.edit { prefs ->

            prefs[Keys.TOTAL_COOKIES] = newCookies
        }

    }
}