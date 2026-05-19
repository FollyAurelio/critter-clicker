package com.example.critter_clicker.data.game.model


data class GameState (
    val totalCookies : Long,

    val spoonLevel : Int,// 1 - 5
    val cauldronLevel : Int, // 1- 5

    val totalCookiesAllTime : Long,
    val totalCookiesClicked : Long,
    val totalCookiesGenerated : Long,

    val currentPet : Int,

    // Blob
    val blobExp: Long,
    val blobHunger: Int,

    // Fireguy
    val fireguyExp: Long,
    val fireguyHunger: Int,

    // Snake
    val snakeExp: Long,
    val snakeHunger: Int,

    // Bird
    val birdExp: Long,
    val birdHunger: Int,

    // Monkey
    val monkeyExp: Long,
    val monkeyHunger: Int,

    // Bot
    val botExp: Long,
    val botHunger: Int,

    val volume : Int, //0 - 100
    val soundEffectOn : Boolean,
    val musicOn : Boolean,
    val gameSpeed : Int,

    val lastPlayedTime: Long
)


