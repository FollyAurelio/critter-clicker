package com.example.critter_clicker.data.game.model


data class GameState (
    val totalCookies : Long,
    val cookiesPerClick : Long,
    val cookiesPerSecond : Long,
    val spoonLevel : Int,// 1 - 5
    val cauldronLevel : Int, // 1- 5

    val totalCookiesAllTime : Long,
    val totalCookiesClicked : Long,
    val totalCookiesGenerated : Long,

    val totalBalls : Int,
    val totalRings : Int,
    val totalFeather : Int,
    val totalCharcoal : Int,

    val currentPet : Int,

    // Blob
    val blobExp: Long,
    val blobHappy: Boolean,
    val blobHunger: Int,

    // Fireguy
    val fireguyExp: Long,
    val fireguyHappy: Boolean,
    val fireguyHunger: Int,

    // Snake
    val snakeExp: Long,
    val snakeHappy: Boolean,
    val snakeHunger: Int,

    // Bird
    val birdExp: Long,
    val birdHappy: Boolean,
    val birdHunger: Int,

    // Monkey
    val monkeyExp: Long,
    val monkeyHappy: Boolean,
    val monkeyHunger: Int,

    // Bot
    val botExp: Long,
    val botHappy: Boolean,
    val botHunger: Int,

    val volume : Int, //0 - 100
    val soundEffectOn : Boolean,
    val musicOn : Boolean,
    val gameSpeed : Int,

    val lastPlayedTime: Long
)


