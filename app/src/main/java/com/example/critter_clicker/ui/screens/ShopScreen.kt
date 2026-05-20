package com.example.critter_clicker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.ui.components.AnimatedImageButton
import com.example.critter_clicker.ui.components.getCookieRepresentation
import com.example.critter_clicker.R

@Composable
fun ShopButton(
    itemName: String,
    itemDescription: String,
    itemImageId: Int,
    itemCost: Long,
    isInStock: Boolean,
    onBuy: () -> Unit,
    viewModel: GameViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val costText =
        "${getCookieRepresentation(itemCost).second}${
            getCookieRepresentation(itemCost).first
        }"

    fun spendCookies() {
        //get the Activity Context

        if (itemCost <= gameState.totalCookies) {
            viewModel.updateCookies(gameState.totalCookies - itemCost)
            if(gameState.soundEffectOn){
                viewModel.soundManager.playSoundEffect(R.raw.cash)
            }
            onBuy()
        } else {
            Toast.makeText(
                context,//activity context
                "Failed to purchase ${itemName}. Too poor :(",//message to display
                Toast.LENGTH_SHORT//duration
            ).show()

        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .border(
                width = 3.dp,
                color = Color.Cyan,

                )
            .padding(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AnimatedImageButton(
                imageId = itemImageId,
                imageSize = 80,
                onClick = {}
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = itemName,
                    fontSize = 20.sp
                )

                Text(
                    text = itemDescription,
                    fontSize = 14.sp
                )
                Row() {
                    Text(
                        text = costText,
                        fontSize = 14.sp
                    )
                    AnimatedImageButton(
                        imageId = R.drawable.cookie,
                        imageSize = 32,
                        onClick = {}
                    )
                }

            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { spendCookies() },
                enabled = isInStock
            ) {
                Text(
                    if (isInStock) "Buy"
                    else "Sold Out"
                )
            }
        }
    }

}


@Composable
fun ShopScreen(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Shop",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        HorizontalDivider()
        //Spoon upgrade button
        val spoonNames =
            listOf("Bronze Spoon", "Silver Spoon", "Gold Spoon", "Platinum Spoon", "Maxed")
        val spoonCosts = listOf(100L, 1000L, 10_000L, 25_000L, 0)
        val cauldronNames =
            listOf("Super Cauldron", "Ultra Cauldron", "Mega Cauldron", "Hyper Cauldron", "Maxed")
        val cauldronCosts = listOf(1000L, 50_000L, 500_000L, 1_000_000L,0)
        ShopButton(
            spoonNames[gameState.spoonLevel - 1],
            "An even better spoon (it's not actually metal, don't worry).",
            R.drawable.spoon,
            spoonCosts[gameState.spoonLevel - 1],
            gameState.spoonLevel < 5,
            onBuy = {
                if (gameState.spoonLevel < 5)
                    viewModel.updateSpoonLevel(gameState.spoonLevel + 1)

            }
        )
        ShopButton(
            cauldronNames[gameState.cauldronLevel - 1],
            "An upgraded cauldron that somehow produces even more delicious cookies (crazy).",
            R.drawable.cauldron,
            cauldronCosts[gameState.cauldronLevel - 1],
            gameState.cauldronLevel < 5,
            onBuy = {
                if (gameState.cauldronLevel < 5)
                    viewModel.updateCauldronLevel(gameState.cauldronLevel + 1)
            }
        )
        //Button to buy the blob
        ShopButton(
            "The Blob",
            "The Blob doesn't require entertainment to survive.",
            R.drawable.blob,
            5,
            gameState.blobExp == 0L,
            { viewModel.updateBlobExp(gameState.blobExp + 1) }
        )
        //Button to buy fireguy
        ShopButton(
            "FireGuy",
            "The hottest one around, has a chronic charcoal addiction.",
            R.drawable.fireguy,
            5000,
            gameState.fireguyExp == 0L,
            { viewModel.updateFireguyExp(gameState.fireguyExp + 1) }
        )
        // Button to buy the snake
        ShopButton(
            "Snake",
            "A true snake in the grass. Loves birds.",
            R.drawable.snake,
            10000,
            gameState.snakeExp == 0L,
            { viewModel.updateSnakeExp(gameState.snakeExp + 1) }
        )

        // Button to buy the bird
        ShopButton(
            "Bird",
            "Never shuts up. Loves jewelery and hates snakes with a passion.",
            R.drawable.bird,
            120000,
            gameState.birdExp == 0L,
            { viewModel.updateBirdExp(gameState.birdExp + 1) }
        )

        // Button to buy the monkey
        ShopButton(
            "Happy Monkey",
            "Loves balls (???).",
            R.drawable.monkey,
            200_000,
            gameState.monkeyExp == 0L,
            { viewModel.updateMonkeyExp(gameState.monkeyExp + 1) }
        )

        // Button to buy the bot
        ShopButton(
            "Bot",
            "The Bot",
            R.drawable.bot,
            500_000,
            gameState.botExp == 0L,
            { viewModel.updateBotExp(gameState.botExp + 1) }
        )
        ShopButton(
            "Water",
            "Water for cookies is a perfectly even exchange, don't worry.",
            R.drawable.water,
            gameState.blobExp * 1,
            gameState.blobExp > 0,
            { viewModel.updateBlobExp(gameState.blobExp + 1) }
        )
        ShopButton(
            "Ball",
            "A Ball. Maybe a monkey would like to play with it.",
            R.drawable.ball,
            gameState.monkeyExp * 5,
            gameState.monkeyExp > 0,
            { viewModel.updateMonkeyExp(gameState.monkeyExp + 1) }
        )
        ShopButton(
            "Feather",
            "A Feather. Maybe a monkey would like to play with it (though I may be wrong).",
            R.drawable.feather,
            gameState.snakeExp * 3,
            gameState.snakeExp > 0,
            { viewModel.updateSnakeExp(gameState.snakeExp + 1) }
        )
        ShopButton(
            "Ring",
            "A Ring. You might not believe this... but maybe a monkey would like to play with it.",
            R.drawable.ring,
            gameState.birdExp * 4,
            gameState.birdExp > 0,
            { viewModel.updateBirdExp(gameState.birdExp + 1) }
        )
        ShopButton(
            "Charcoal",
            "Some Charcoal. Good for fire, and fireguys.",
            R.drawable.coal,
            gameState.fireguyExp * 2,
            gameState.fireguyExp > 0,
            { viewModel.updateFireguyExp(gameState.fireguyExp + 1) }
        )
        ShopButton(
            "Gears",
            "Gears make the world go round, almost as much so than cookies.",
            R.drawable.gear,
            gameState.botExp * 6,
            gameState.botExp > 0,
            { viewModel.updateBotExp(gameState.botExp + 1) }
        )
    }


}


