package com.example.critter_clicker.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.critter_clicker.R
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.ui.components.AnimatedImageButton
import kotlinx.coroutines.delay


data class FloatingText(
    val id: Int, val text: String, val position: Offset
)

@Composable
fun DisapperingText(text: String, duration: Long, position: Offset) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(duration)
        visible = false
    }
    AnimatedVisibility(
        visible = visible, exit = fadeOut()
    ) {
        Text(
            text = text, fontSize = 24.sp,
            color = Color.White
        )
    }
}


@Composable
fun CauldronScreen(viewModel: GameViewModel = viewModel()) {

    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    var tapPosition by remember { mutableStateOf(Offset.Zero) }

    var floatingTexts by remember {
        mutableStateOf(listOf<FloatingText>())
    }
    //This box center aligns the cauldron and the text
    Text(
        text = "Cauldron",
        fontSize = 32.sp,
        modifier = Modifier.padding(16.dp)
    )
    HorizontalDivider()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    tapPosition = offset


                }
            }, contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // On click, increment cookies and display text
            AnimatedImageButton(
                imageId = R.drawable.cauldron, imageSize = 200, onClick = {
                    floatingTexts = floatingTexts + FloatingText(
                        id = floatingTexts.size,
                        text = "+${gameState.cookiesPerClick}",
                        position = tapPosition
                    )
                    viewModel.updateCookies(gameState.totalCookies + gameState.cookiesPerClick)
                    viewModel.updateTotalCookiesClicked(gameState.totalCookiesClicked + gameState.cookiesPerClick)
                    viewModel.updateTotalCookiesAllTime(gameState.totalCookiesAllTime + gameState.cookiesPerClick)
                })

            Text("Click the cauldron to bake cookies!")
        }

        floatingTexts.forEach { item ->
            DisapperingText(
                text = item.text, 1000, position = item.position
            )
        }
    }
}