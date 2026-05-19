package com.example.critter_clicker.data.game

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore by preferencesDataStore("game")

object Keys {

    val TOTAL_COOKIES =
        longPreferencesKey("totalCookies")

    val COOKIES_PER_CLICK =
        longPreferencesKey("cookiesPerClick")

    val COOKIES_PER_SECOND =
        longPreferencesKey("cookiesPerSecond")

    val SPOON_LEVEL =
        intPreferencesKey("spoonLevel")

    val CAULDRON_LEVEL =
        intPreferencesKey("cauldronLevel")

    val TOTAL_COOKIES_ALL_TIME =
        longPreferencesKey("totalCookiesAllTime")

    val TOTAL_COOKIES_CLICKED =
        longPreferencesKey("totalCookiesClicked")

    val TOTAL_COOKIES_GENERATED =
        longPreferencesKey("totalCookiesGenerated")

    val TOTAL_BALLS =
        intPreferencesKey("totalBalls")

    val TOTAL_RINGS =
        intPreferencesKey("totalRings")

    val TOTAL_FEATHER =
        intPreferencesKey("totalFeather")

    val TOTAL_CHARCOAL =
        intPreferencesKey("totalCharcoal")

    val BLOB_EXP =
        longPreferencesKey("blobExp")

    val BLOB_HAPPY =
        booleanPreferencesKey("blobHappy")

    val BLOB_HUNGER =
        intPreferencesKey("blobHunger")


    val FIREGUY_EXP =
        longPreferencesKey("fireguyExp")

    val FIREGUY_HAPPY =
        booleanPreferencesKey("fireguyHappy")

    val FIREGUY_HUNGER =
        intPreferencesKey("fireguyHunger")


    val SNAKE_EXP =
        longPreferencesKey("snakeExp")

    val SNAKE_HAPPY =
        booleanPreferencesKey("snakeHappy")

    val SNAKE_HUNGER =
        intPreferencesKey("snakeHunger")


    val BIRD_EXP =
        longPreferencesKey("birdExp")

    val BIRD_HAPPY =
        booleanPreferencesKey("birdHappy")

    val BIRD_HUNGER =
        intPreferencesKey("birdHunger")


    val MONKEY_EXP =
        longPreferencesKey("monkeyExp")

    val MONKEY_HAPPY =
        booleanPreferencesKey("monkeyHappy")

    val MONKEY_HUNGER =
        intPreferencesKey("monkeyHunger")

    val BOT_EXP =
        longPreferencesKey("botExp")

    val BOT_HAPPY =
        booleanPreferencesKey("botHappy")

    val BOT_HUNGER =
        intPreferencesKey("botHunger")
}




