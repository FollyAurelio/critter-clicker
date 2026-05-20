package com.example.critter_clicker.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.ui.components.getCookieRepresentation

fun shareScore(context: Context, score: Long) {
    val shareText = "I have $score cookies in Critter Clicker! 🍪"

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share your score"))
}
@Composable
fun StatsScreen(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val context = LocalContext.current
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

        Text(
            "General",
            fontSize = 32.sp
        )
        HorizontalDivider()
        Text(text = "Cookies in Inventory : ${gameState.totalCookies} cookies")
        Text(text = "Cookies transmutted (all time) : ${gameState.totalCookiesAllTime}")
        Text(text = "Cookies per press : ${viewModel.getCookiesPerClick()}")
        Text(text = "Cookies per second : ${viewModel.getCookiesPerSecond()}")
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

        Button(onClick = {shareScore(context = context, gameState.totalCookies)}){
            Text(text = "Share your Score")
        }
    }
}