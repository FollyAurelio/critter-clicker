package com.example.critter_clicker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.critter_clicker.data.game.GameViewModel

@Composable
fun StatsScreen(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        val spoonNames =
            listOf("Wooden Spoon", "Bronze Spoon", "Silver Spoon", "Gold Spoon", "Platinum Spoon")
        val cauldronNames =
            listOf(
                "Cauldron",
                "Super Cauldron",
                "Ultra Cauldron",
                "Mega Cauldron",
                "Hyper Cauldron"
            )
        Text(
            text = "Stats",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalDivider()
        Text(
            "General",
            fontSize = 32.sp
        )
        HorizontalDivider()
        Text(text = "Cookies in Inventory : ${gameState.totalCookies} cookies")
        Text(text = "Balls in Inventory : ${gameState.totalBalls} balls")
        Text(text = "Feathers in Inventory : ${gameState.totalFeather} feathers")
        Text(text = "Rings in Inventory : ${gameState.totalRings} rings")
        Text(text = "Charcoal in Inventory : ${gameState.totalCharcoal} charcoal")
        Text(
            "Milestones",
            fontSize = 32.sp
        )
        HorizontalDivider()
        Text(text = "Cookies transmutted (all time) : ${gameState.totalCookiesAllTime}")
        Text(text = "Cookies per press : ${gameState.cookiesPerClick}")
        Text(text = "Cookies per second : ${gameState.cookiesPerSecond}")
        Text(text = "Cookies generated via spoon : ${gameState.totalCookiesClicked}")
        Text(text = "Cookies generated via cauldron : ${gameState.totalCookiesGenerated}")
        Text(
            "Upgrades",
            fontSize = 32.sp
        )
        HorizontalDivider()
        Text(text = "Spoon level : ${spoonNames[gameState.spoonLevel - 1]}")
        Text(text = "Cauldron level : ${cauldronNames[gameState.cauldronLevel - 1]}")
        Text(text = "Total Blob exp : ${gameState.blobExp}")
        Text(text = "Total Fireguy exp : ${gameState.fireguyExp}")
        Text(text = "Total Snake exp : ${gameState.snakeExp}")
        Text(text = "Total Bird exp : ${gameState.birdExp}")
        Text(text = "Total Monkey exp : ${gameState.monkeyExp}")
        Text(text = "Total Bot exp : ${gameState.botExp}")


    }
}