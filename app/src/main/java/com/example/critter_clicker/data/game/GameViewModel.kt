package com.example.critter_clicker.data.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.critter_clicker.data.game.model.GameState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        GameRepository(application)

    val gameState: StateFlow<GameState> =
        repository.gameFlow.stateIn(
            scope = viewModelScope,
            started =
                SharingStarted.WhileSubscribed(5000),

            initialValue = GameState(

                totalCookies = 0L,
                cookiesPerClick = 1L,
                cookiesPerSecond = 0L,
                spoonLevel = 1,
                cauldronLevel = 1,

                totalCookiesAllTime = 0L,
                totalCookiesClicked = 0L,
                totalCookiesGenerated = 0L,

                totalBalls = 0,
                totalRings = 0,
                totalFeather = 0,
                totalCharcoal = 0,

                blobExp = 0L,
                blobHappy = true,
                blobHunger = 5000,

                fireguyExp = 0L,
                fireguyHappy = true,
                fireguyHunger = 5000,

                snakeExp = 0L,
                snakeHappy = true,
                snakeHunger = 5000,

                birdExp = 0L,
                birdHappy = true,
                birdHunger = 5000,

                monkeyExp = 0L,
                monkeyHappy = true,
                monkeyHunger = 5000,

                botExp = 0L,
                botHappy = true,
                botHunger = 5000,

                volume = 100,
                soundEffectOn = true,
                musicOn = true,
                gameSpeed = 1,

            )
        )

    fun updateCookies(newCookies: Long) {

        viewModelScope.launch {
            repository.updateCookies(newCookies)
        }
    }

    fun updateCookiesPerClick(value: Long) {
        viewModelScope.launch {
            repository.updateCookiesPerClick(value)
        }
    }

    fun updateCookiesPerSecond(value: Long) {
        viewModelScope.launch {
            repository.updateCookiesPerSecond(value)
        }
    }

    fun updateSpoonLevel(value: Int) {
        viewModelScope.launch {
            repository.updateSpoonLevel(value)
        }
    }

    fun updateCauldronLevel(value: Int) {
        viewModelScope.launch {
            repository.updateCauldronLevel(value)
        }
    }

    fun updateBalls(value: Int) {
        viewModelScope.launch {
            repository.updateBalls(value)
        }
    }

    fun updateRings(value: Int) {
        viewModelScope.launch {
            repository.updateRings(value)
        }
    }

    fun updateFeather(value: Int) {
        viewModelScope.launch {
            repository.updateFeather(value)
        }
    }

    fun updateCharcoal(value: Int) {
        viewModelScope.launch {
            repository.updateCharcoal(value)
        }
    }

    fun updateTotalCookiesAllTime(value: Long) {
        viewModelScope.launch {
            repository.updateTotalCookiesAllTime(value)
        }
    }

    fun updateTotalCookiesClicked(value: Long) {
        viewModelScope.launch {
            repository.updateTotalCookiesClicked(value)
        }
    }

    fun updateTotalCookiesGenerated(value: Long) {
        viewModelScope.launch {
            repository.updateTotalCookiesGenerated(value)
        }
    }

    fun updateBlobExp(value: Long) {
        viewModelScope.launch {
            repository.updateBlobExp(value)
        }
    }

    fun updateBlobHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateBlobHappy(value)
        }
    }

    fun updateBlobHunger(value: Int) {
        viewModelScope.launch {
            repository.updateBlobHunger(value)
        }
    }


    fun updateFireguyExp(value: Long) {
        viewModelScope.launch {
            repository.updateFireguyExp(value)
        }
    }

    fun updateFireguyHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateFireguyHappy(value)
        }
    }

    fun updateFireguyHunger(value: Int) {
        viewModelScope.launch {
            repository.updateFireguyHunger(value)
        }
    }


    fun updateSnakeExp(value: Long) {
        viewModelScope.launch {
            repository.updateSnakeExp(value)
        }
    }

    fun updateSnakeHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateSnakeHappy(value)
        }
    }

    fun updateSnakeHunger(value: Int) {
        viewModelScope.launch {
            repository.updateSnakeHunger(value)
        }
    }


    fun updateBirdExp(value: Long) {
        viewModelScope.launch {
            repository.updateBirdExp(value)
        }
    }

    fun updateBirdHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateBirdHappy(value)
        }
    }

    fun updateBirdHunger(value: Int) {
        viewModelScope.launch {
            repository.updateBirdHunger(value)
        }
    }


    fun updateMonkeyExp(value: Long) {
        viewModelScope.launch {
            repository.updateMonkeyExp(value)
        }
    }

    fun updateMonkeyHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateMonkeyHappy(value)
        }
    }

    fun updateMonkeyHunger(value: Int) {
        viewModelScope.launch {
            repository.updateMonkeyHunger(value)
        }
    }


    fun updateBotExp(value: Long) {
        viewModelScope.launch {
            repository.updateBotExp(value)
        }
    }

    fun updateBotHappy(value: Boolean) {
        viewModelScope.launch {
            repository.updateBotHappy(value)
        }
    }

    fun updateBotHunger(value: Int) {
        viewModelScope.launch {
            repository.updateBotHunger(value)
        }
    }

    fun updateVolume(value: Int) {
        viewModelScope.launch {
            repository.updateVolume(value)
        }
    }

    fun updateSoundEffectOn(value: Boolean) {
        viewModelScope.launch {
            repository.updateSoundEffectOn(value)
        }
    }

    fun updateMusicOn(value: Boolean) {
        viewModelScope.launch {
            repository.updateMusicOn(value)
        }
    }

    fun updateGameSpeed(value: Int) {
        viewModelScope.launch {
            repository.updateGameSpeed(value)
        }
    }

    fun resetGame() {
        viewModelScope.launch {
            repository.resetGame()
        }
    }

}

