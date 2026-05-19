package com.example.critter_clicker.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@Composable
fun PetListButton(
    petId: PetType,
    petName: String,
    petHunger: Int,
    petImageId: Int,
    petExp: Long,
    navController: NavHostController,
    viewModel: GameViewModel = viewModel()
) {
    if (petId == PetType.NONE) {
        return
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
                imageId = petImageId,
                imageSize = 80,
                onClick = {}
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = petName,
                    fontSize = 20.sp
                )

                LinearProgressIndicator(
                    progress = { petHunger / 5000.0f }
                )
                Text("Hunger : $petHunger")



                Text(
                    "Exp : $petExp",
                    color = Color.Blue
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {viewModel.updateCurrentPet(petId.ordinal)
                          navController.navigate(AppScreens.Pets.name)},
                ) {
                Text(
                    "Select"
                )
            }
        }
    }


}

@Composable
fun PetListScreen(navController: NavHostController, viewModel: GameViewModel = viewModel()) {
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

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Pet List",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        HorizontalDivider()
        PetListButton(
            petId = PetType.NONE,
            petName = petNames[PetType.NONE.ordinal],
            petHunger = 0,
            petImageId = 0,
            petExp = 0,
            navController
        )

        if (gameState.blobExp != 0L) {
            PetListButton(
                petId = PetType.BLOB,
                petName = petNames[PetType.BLOB.ordinal],
                petHunger = gameState.blobHunger,
                petImageId = petImages[PetType.BLOB.ordinal],
                petExp = gameState.blobExp,
                navController
            )
        }
        if (gameState.fireguyExp != 0L) {
            PetListButton(
                petId = PetType.FIREGUY,
                petName = petNames[PetType.FIREGUY.ordinal],
                petHunger = gameState.fireguyHunger,
                petImageId = petImages[PetType.FIREGUY.ordinal],
                petExp = gameState.fireguyExp,
                navController
            )
        }
        if (gameState.snakeExp != 0L) {
            PetListButton(
                petId = PetType.SNAKE,
                petName = petNames[PetType.SNAKE.ordinal],
                petHunger = gameState.snakeHunger,
                petImageId = petImages[PetType.SNAKE.ordinal],
                petExp = gameState.snakeExp,
                navController
            )
        }
        if (gameState.birdExp != 0L) {
            PetListButton(
                petId = PetType.BIRD,
                petName = petNames[PetType.BIRD.ordinal],
                petHunger = gameState.birdHunger,
                petImageId = petImages[PetType.BIRD.ordinal],
                petExp = gameState.birdExp,
                navController
            )
        }
        if (gameState.monkeyExp != 0L) {
            PetListButton(
                petId = PetType.MONKEY,
                petName = petNames[PetType.MONKEY.ordinal],
                petHunger = gameState.monkeyHunger,
                petImageId = petImages[PetType.MONKEY.ordinal],
                petExp = gameState.monkeyExp,
                navController
            )
        }
        if (gameState.botExp != 0L) {
            PetListButton(
                petId = PetType.BOT,
                petName = petNames[PetType.BOT.ordinal],
                petHunger = gameState.botHunger,
                petImageId = petImages[PetType.BOT.ordinal],
                petExp = gameState.botExp,
                navController
            )
        }
    }
}


