package com.example.critter_clicker.ui.screens

import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.critter_clicker.data.game.GameViewModel

@Composable
fun SettingsScreen(
    viewModel: GameViewModel = viewModel()
) {

    val gameState by
    viewModel.gameState.collectAsStateWithLifecycle()

    var showDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Settings",
            fontSize = 32.sp
        )

        HorizontalDivider()

        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Volume",
                    fontSize = 20.sp
                )

                Text(
                    text = "${gameState.volume}",
                    fontSize = 20.sp
                )
            }

            Slider(
                value = gameState.volume.toFloat(),

                onValueChange = {
                    viewModel.updateVolume(it.toInt())
                },

                valueRange = 0f..100f
            )
        }

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Music",
                fontSize = 20.sp
            )

            Switch(
                checked = gameState.musicOn,

                onCheckedChange = {
                    viewModel.updateMusicOn(it)
                }
            )
        }

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Sound Effects",
                fontSize = 20.sp
            )

            Switch(
                checked = gameState.soundEffectOn,

                onCheckedChange = {
                    viewModel.updateSoundEffectOn(it)
                }
            )
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = "Game Speed",
                fontSize = 20.sp
            )

            Text(
                text = "${gameState.gameSpeed}",
                fontSize = 20.sp
            )
        }

        Slider(
            value = gameState.gameSpeed.toFloat(),

            onValueChange = {
                viewModel.updateGameSpeed(it.toInt())
            },

            valueRange = 1f..5f,
            steps = 3
        )

        Button(onClick = {showDialog = true}) {
            Text("Delete save data",
                color = Color.Red)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text("Reset Save?")
                },
                text = {
                    Text("This cannot be undone.")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            viewModel.resetGame()
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {

                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }

    }
}

