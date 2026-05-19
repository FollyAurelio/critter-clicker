package com.example.critter_clicker.data.game

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.critter_clicker.data.game.model.GameState
import kotlinx.coroutines.flow.map

class GameRepository(private val context: Context) {

    val gameFlow = context.dataStore.data.map { prefs ->

        GameState(
            totalCookies =
                prefs[Keys.TOTAL_COOKIES] ?: 0L,
            cookiesPerClick =
                prefs[Keys.COOKIES_PER_CLICK] ?: 1L,
            cookiesPerSecond =
                prefs[Keys.COOKIES_PER_SECOND] ?: 0L,
            spoonLevel =
                prefs[Keys.SPOON_LEVEL] ?: 1,
            cauldronLevel =
                prefs[Keys.CAULDRON_LEVEL] ?: 1,
            totalCookiesAllTime =
                prefs[Keys.TOTAL_COOKIES_ALL_TIME] ?: 0L,

            totalCookiesClicked =
                prefs[Keys.TOTAL_COOKIES_CLICKED] ?: 0L,

            totalCookiesGenerated =
                prefs[Keys.TOTAL_COOKIES_GENERATED] ?: 0L,
            totalBalls =
                prefs[Keys.TOTAL_BALLS] ?: 0,
            totalRings =
                prefs[Keys.TOTAL_RINGS] ?: 0,
            totalFeather =
                prefs[Keys.TOTAL_FEATHER] ?: 0,
            totalCharcoal =
                prefs[Keys.TOTAL_CHARCOAL] ?: 0,

            currentPet =
                prefs[Keys.CURRENT_PET] ?: 0,

            blobExp = prefs[Keys.BLOB_EXP] ?: 0L,
            blobHappy = prefs[Keys.BLOB_HAPPY] ?: true,
            blobHunger = prefs[Keys.BLOB_HUNGER] ?: 5000,

            fireguyExp = prefs[Keys.FIREGUY_EXP] ?: 0L,
            fireguyHappy = prefs[Keys.FIREGUY_HAPPY] ?: true,
            fireguyHunger = prefs[Keys.FIREGUY_HUNGER] ?: 5000,

            snakeExp = prefs[Keys.SNAKE_EXP] ?: 0L,
            snakeHappy = prefs[Keys.SNAKE_HAPPY] ?: true,
            snakeHunger = prefs[Keys.SNAKE_HUNGER] ?: 5000,

            birdExp = prefs[Keys.BIRD_EXP] ?: 0L,
            birdHappy = prefs[Keys.BIRD_HAPPY] ?: true,
            birdHunger = prefs[Keys.BIRD_HUNGER] ?: 5000,

            monkeyExp = prefs[Keys.MONKEY_EXP] ?: 0L,
            monkeyHappy = prefs[Keys.MONKEY_HAPPY] ?: true,
            monkeyHunger = prefs[Keys.MONKEY_HUNGER] ?: 5000,

            botExp = prefs[Keys.BOT_EXP] ?: 0L,
            botHappy = prefs[Keys.BOT_HAPPY] ?: true,
            botHunger = prefs[Keys.BOT_HUNGER] ?: 5000,

            volume = prefs[Keys.VOLUME] ?: 100,
            soundEffectOn = prefs[Keys.SOUND_EFFECT_ON] ?: true,
            musicOn = prefs[Keys.MUSIC_ON] ?: true,

            gameSpeed = prefs[Keys.GAME_SPEED] ?: 1,
        )
    }

    suspend fun updateCookies(value: Long) {
        context.dataStore.edit { prefs ->

            prefs[Keys.TOTAL_COOKIES] = value
        }
    }

    suspend fun updateCookiesPerClick(value: Long) {
        context.dataStore.edit {
            it[Keys.COOKIES_PER_CLICK] = value
        }
    }

    suspend fun updateCookiesPerSecond(value: Long) {
        context.dataStore.edit {
            it[Keys.COOKIES_PER_SECOND] = value
        }
    }

    suspend fun updateSpoonLevel(value: Int) {
        context.dataStore.edit {
            it[Keys.SPOON_LEVEL] = value
        }
    }

    suspend fun updateCauldronLevel(value: Int) {
        context.dataStore.edit {
            it[Keys.CAULDRON_LEVEL] = value
        }
    }

    suspend fun updateTotalCookiesAllTime(value: Long) {
        context.dataStore.edit {
            it[Keys.TOTAL_COOKIES_ALL_TIME] = value
        }
    }

    suspend fun updateTotalCookiesClicked(value: Long) {
        context.dataStore.edit {
            it[Keys.TOTAL_COOKIES_CLICKED] = value
        }
    }

    suspend fun updateTotalCookiesGenerated(value: Long) {
        context.dataStore.edit {
            it[Keys.TOTAL_COOKIES_GENERATED] = value
        }
    }

    suspend fun updateBalls(value: Int) {
        context.dataStore.edit {
            it[Keys.TOTAL_BALLS] = value
        }
    }

    suspend fun updateRings(value: Int) {
        context.dataStore.edit {
            it[Keys.TOTAL_RINGS] = value
        }
    }

    suspend fun updateFeather(value: Int) {
        context.dataStore.edit {
            it[Keys.TOTAL_FEATHER] = value
        }
    }

    suspend fun updateCharcoal(value: Int) {
        context.dataStore.edit {
            it[Keys.TOTAL_CHARCOAL] = value
        }
    }

    suspend fun updateCurrentPet(value: Int) {
        context.dataStore.edit {
            it[Keys.CURRENT_PET] = value
        }
    }

    suspend fun updateBlobExp(value: Long) {
        context.dataStore.edit {
            it[Keys.BLOB_EXP] = value
        }
    }

    suspend fun updateBlobHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.BLOB_HAPPY] = value
        }
    }

    suspend fun updateBlobHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.BLOB_HUNGER] = value
        }
    }


    suspend fun updateFireguyExp(value: Long) {
        context.dataStore.edit {
            it[Keys.FIREGUY_EXP] = value
        }
    }

    suspend fun updateFireguyHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.FIREGUY_HAPPY] = value
        }
    }

    suspend fun updateFireguyHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.FIREGUY_HUNGER] = value
        }
    }


    suspend fun updateSnakeExp(value: Long) {
        context.dataStore.edit {
            it[Keys.SNAKE_EXP] = value
        }
    }

    suspend fun updateSnakeHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.SNAKE_HAPPY] = value
        }
    }

    suspend fun updateSnakeHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.SNAKE_HUNGER] = value
        }
    }


    suspend fun updateBirdExp(value: Long) {
        context.dataStore.edit {
            it[Keys.BIRD_EXP] = value
        }
    }

    suspend fun updateBirdHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.BIRD_HAPPY] = value
        }
    }

    suspend fun updateBirdHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.BIRD_HUNGER] = value
        }
    }


    suspend fun updateMonkeyExp(value: Long) {
        context.dataStore.edit {
            it[Keys.MONKEY_EXP] = value
        }
    }

    suspend fun updateMonkeyHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.MONKEY_HAPPY] = value
        }
    }

    suspend fun updateMonkeyHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.MONKEY_HUNGER] = value
        }
    }


    suspend fun updateBotExp(value: Long) {
        context.dataStore.edit {
            it[Keys.BOT_EXP] = value
        }
    }

    suspend fun updateBotHappy(value: Boolean) {
        context.dataStore.edit {
            it[Keys.BOT_HAPPY] = value
        }
    }

    suspend fun updateBotHunger(value: Int) {
        context.dataStore.edit {
            it[Keys.BOT_HUNGER] = value
        }
    }

    suspend fun updateVolume(value: Int) {
        context.dataStore.edit {
            it[Keys.VOLUME] = value
        }
    }

    suspend fun updateSoundEffectOn(value: Boolean) {
        context.dataStore.edit {
            it[Keys.SOUND_EFFECT_ON] = value
        }
    }

    suspend fun updateMusicOn(value: Boolean) {
        context.dataStore.edit {
            it[Keys.MUSIC_ON] = value
        }
    }

    suspend fun updateGameSpeed(value: Int) {
        context.dataStore.edit {
            it[Keys.GAME_SPEED] = value
        }
    }


    suspend fun resetGame() {
        context.dataStore.edit {
            it.clear()
        }
    }
}



