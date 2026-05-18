package com.example.critter_clicker.data.settings

import android.content.Context
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore



val Context.dataStore by preferencesDataStore("settings")

object Keys {
    val TOTAL_COOKIES = longPreferencesKey("totalCookies")
}

