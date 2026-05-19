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
import com.example.critter_clicker.R
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.data.game.model.PetType


@Composable
fun PetListButton(
    petId : PetType,
    petName: String,
    petHunger : Int,
    petImageId: Int,
    petHappy: Boolean,
    petExp: Boolean,
    viewModel: GameViewModel = viewModel()
) {


}
@Composable
fun PetListScreen(viewModel: GameViewModel = viewModel()) {
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
            PetType.NONE,
            petNames[PetType.NONE.ordinal],
            0,
            0,
            false,
            false
        )
    }
}


