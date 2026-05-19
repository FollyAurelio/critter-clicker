package com.example.critter_clicker.ui.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.critter_clicker.R
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.data.game.model.PetType
import com.example.critter_clicker.ui.components.AnimatedImageButton
import kotlin.collections.plus


@Composable
fun FeedPetButton(
    viewModel: GameViewModel = viewModel()
) {

    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var showFeedDialog by remember { mutableStateOf(false) }
    var cookieAmount by remember { mutableStateOf("1") }

    val petNames = listOf(
        "None",
        "Blob",
        "FireGuy",
        "Snake",
        "Bird",
        "Monkey",
        "Bot"
    )

    fun feedPet(amount: Int) {

        when (gameState.currentPet) {

            PetType.BLOB.ordinal ->
                viewModel.updateBlobHunger(
                    minOf(
                        5000,
                        gameState.blobHunger + amount
                    )
                )

            PetType.FIREGUY.ordinal ->
                viewModel.updateFireguyHunger(
                    minOf(
                        5000,
                        gameState.fireguyHunger + amount
                    )
                )

            PetType.SNAKE.ordinal ->
                viewModel.updateSnakeHunger(
                    minOf(
                        5000,
                        gameState.snakeHunger + amount
                    )
                )

            PetType.BIRD.ordinal ->
                viewModel.updateBirdHunger(
                    minOf(
                        5000,
                        gameState.birdHunger + amount
                    )
                )

            PetType.MONKEY.ordinal ->
                viewModel.updateMonkeyHunger(
                    minOf(
                        5000,
                        gameState.monkeyHunger + amount
                    )
                )

            PetType.BOT.ordinal ->
                viewModel.updateBotHunger(
                    minOf(
                        5000,
                        gameState.botHunger + amount
                    )
                )
        }
    }

    Button(
        onClick = { showFeedDialog = true },
        enabled = gameState.currentPet != PetType.NONE.ordinal
    ) {
        Text("Feed")
    }

    if (showFeedDialog) {

        AlertDialog(

            onDismissRequest = {
                showFeedDialog = false
            },

            title = {
                Text(
                    "Feed Cookies to ${petNames[gameState.currentPet]}"
                )
            },

            text = {

                Column {

                    Text("How many cookies?")

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Button(
                            onClick = {

                                val value =
                                    cookieAmount.toIntOrNull() ?: 1

                                if (value > 1)
                                    cookieAmount =
                                        (value - 1).toString()
                            }
                        ) {
                            Text("-")
                        }

                        TextField(
                            value = cookieAmount,
                            onValueChange = { cookieAmount = it },
                            modifier = Modifier.width(100.dp)
                        )

                        Button(
                            onClick = {

                                val value =
                                    cookieAmount.toIntOrNull() ?: 1

                                cookieAmount =
                                    (value + 1).toString()
                            }
                        ) {
                            Text("+")
                        }
                    }
                }
            },

            confirmButton = {

                Button(
                    onClick = {

                        val amount =
                            cookieAmount.toIntOrNull() ?: 0

                        if (
                            gameState.totalCookies >= amount
                        ) {

                            viewModel.updateCookies(
                                gameState.totalCookies - amount
                            )

                            feedPet(amount)

                            Toast.makeText(
                                context,
                                "Fed $amount cookies to ${petNames[gameState.currentPet]}",
                                Toast.LENGTH_SHORT
                            ).show()

                            showFeedDialog = false
                        }

                        else {

                            Toast.makeText(
                                context,
                                "Not enough cookies",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Confirm")
                }
            },

            dismissButton = {

                Button(
                    onClick = {
                        showFeedDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
@Composable
fun PetScreen(navController: NavHostController, viewModel: GameViewModel = viewModel()) {

    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    val petImages = listOf(
        0,
        R.drawable.blob,
        R.drawable.fireguy,
        R.drawable.snake,
        R.drawable.bird,
        R.drawable.monkey,
        R.drawable.bot
    )
    val petNames = listOf("", "The Blob", "Fireguy", "Snake", "Bird", "Monkey", "Bot")
    val petItems = listOf("","Water", "Charcoal", "Feather", "Ring", "Ball", "Bolts")
    Column(){
        Text(
            text = "Pets",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ){

            FeedPetButton()

            Button(onClick = {navController.navigate(AppScreens.PetList.name)}){
                Text(text = "List")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {

            // On click, increment cookies and display text
            if(gameState.currentPet != 0) {
                AnimatedImageButton(
                    imageId = petImages[gameState.currentPet], imageSize = 200, onClick = {})
            }else{
                Text(text = "No Pet!",
                    fontSize = 32.sp)
            }

        }
    }

}

