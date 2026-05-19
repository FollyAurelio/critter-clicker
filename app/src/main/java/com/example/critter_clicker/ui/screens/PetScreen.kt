package com.example.critter_clicker.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.critter_clicker.R
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.ui.components.AnimatedImageButton
import kotlin.collections.plus

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
            Button(onClick = {}){
                Text(text = "Toys")
            }

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

