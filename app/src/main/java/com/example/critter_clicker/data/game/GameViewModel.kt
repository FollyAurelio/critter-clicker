package com.example.critter_clicker.data.game

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.critter_clicker.data.game.model.GameState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Math.pow
import kotlin.math.pow

class GameViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val repository = GameRepository(application)

    val gameState: StateFlow<GameState> = repository.gameFlow.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000),

        initialValue = GameState(

            totalCookies = 0L,

            spoonLevel = 1,
            cauldronLevel = 1,

            totalCookiesAllTime = 0L,
            totalCookiesClicked = 0L,
            totalCookiesGenerated = 0L,
            currentPet = 0,

            blobExp = 0L,
            blobHunger = 5000,

            fireguyExp = 0L,
            fireguyHunger = 5000,

            snakeExp = 0L,
            snakeHunger = 5000,

            birdExp = 0L,
            birdHunger = 5000,

            monkeyExp = 0L,
            monkeyHunger = 5000,

            botExp = 0L,
            botHunger = 5000,

            volume = 100,
            soundEffectOn = true,
            musicOn = true,
            gameSpeed = 1,
            lastPlayedTime = System.currentTimeMillis(),

            )
    )


    fun updateCookies(newCookies: Long) {

        viewModelScope.launch {
            repository.updateCookies(newCookies)
        }
    }

    fun getCookiesPerClick(): Long {

        val currentState = gameState.value
        return currentState.spoonLevel.toDouble().pow(2.0).toLong() * currentState.gameSpeed


    }

    fun getCookiesPerSecond(): Long {

        val currentState = gameState.value

        fun isZero(exp: Long, hunger: Int): Long {
            if (hunger <= 0) {
                return 0L
            }
            return exp
        }
        return (
                isZero(
                    currentState.blobExp,
                    currentState.blobHunger
                ) +
                        isZero(
                            currentState.fireguyExp,
                            currentState.fireguyHunger
                        ) +
                        isZero(
                            currentState.snakeExp,
                            currentState.snakeHunger
                        ) +
                        isZero(
                            currentState.birdExp,
                            currentState.birdHunger
                        ) +
                        isZero(
                            currentState.monkeyExp,
                            currentState.monkeyHunger
                        ) +
                        isZero(
                            currentState.botExp,
                            currentState.botHunger
                        )
                ) * currentState.cauldronLevel * currentState.gameSpeed
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


    fun updateCurrentPet(value: Int) {
        viewModelScope.launch {
            repository.updateCurrentPet(value)
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

    fun updateLastPlayedTime(value: Long) {
        viewModelScope.launch {
            repository.updateLastPlayedTime(value)
        }
    }
    fun handleClick() {
        viewModelScope.launch {
            val currentState = gameState.value
            updateCookies(currentState.totalCookies + getCookiesPerClick())
            updateTotalCookiesClicked(currentState.totalCookiesClicked + getCookiesPerClick())
            updateTotalCookiesAllTime(currentState.totalCookiesAllTime + getCookiesPerClick()) // single atomic operation
        }

    }

    fun offlineCalculations() {

        viewModelScope.launch {

            val currentTime = System.currentTimeMillis()

            val currentState = repository.gameFlow.first()

            val elapsedSeconds =
                ((currentTime - currentState.lastPlayedTime) / 1000) * currentState.gameSpeed

            fun petOfflineProduction(
                exp: Long,
                hunger: Int
            ): Long {

                val activeSeconds =
                    minOf(elapsedSeconds, hunger.toLong())

                return exp * activeSeconds
            }
            val offlineEarnings = (

                    petOfflineProduction(
                        currentState.blobExp,
                        currentState.blobHunger) +

                            petOfflineProduction(
                                currentState.fireguyExp,
                                currentState.fireguyHunger
                            ) +

                            petOfflineProduction(
                                currentState.snakeExp,
                                currentState.snakeHunger
                            ) +

                            petOfflineProduction(
                                currentState.birdExp,
                                currentState.birdHunger
                            ) +

                            petOfflineProduction(
                                currentState.monkeyExp,
                                currentState.monkeyHunger
                            ) +

                            petOfflineProduction(
                                currentState.botExp,
                                currentState.botHunger
                            )

                    ) * currentState.cauldronLevel
            updateCookies(
                currentState.totalCookies + offlineEarnings
            )
            updateTotalCookiesGenerated(
                currentState.totalCookiesGenerated + offlineEarnings
            )
            updateTotalCookiesAllTime(
                currentState.totalCookiesAllTime + offlineEarnings
            )
            updateBlobHunger(
                maxOf(
                    0,
                    currentState.blobHunger - elapsedSeconds.toInt()
                )
            )
            updateFireguyHunger(
                maxOf(
                    0,
                    currentState.fireguyHunger - elapsedSeconds.toInt()
                )
            )
            updateSnakeHunger(
                maxOf(
                    0,
                    currentState.snakeHunger - elapsedSeconds.toInt()
                )
            )
            updateBirdHunger(
                maxOf(
                    0,
                    currentState.birdHunger - elapsedSeconds.toInt()
                )
            )
            updateMonkeyHunger(
                maxOf(
                    0,
                    currentState.monkeyHunger - elapsedSeconds.toInt()
                )
            )
            updateBotHunger(
                maxOf(
                    0,
                    currentState.botHunger - elapsedSeconds.toInt()
                )
            )
            updateLastPlayedTime(currentTime)
        }
    }


}

