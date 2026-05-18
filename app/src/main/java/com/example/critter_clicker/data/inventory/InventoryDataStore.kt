package com.example.critter_clicker.data.inventory

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore



val Context.dataStore by preferencesDataStore("inventory")

object Keys {
    val TOTAL_COOKIES = longPreferencesKey("totalCookies")
    val COOKIES_PER_CLICK = longPreferencesKey("cookiesPerClick")
}




