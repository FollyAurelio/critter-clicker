package com.example.critter_clicker.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//Animated springy button
@Composable
fun AnimatedImageButton(imageId: Int, imageSize: Int, onClick: () -> Unit) {
    var isClicked by rememberSaveable { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 0.85f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    Image(
        painter = painterResource(imageId),
        contentDescription = "Click me",
        modifier = Modifier
            .size(imageSize.dp)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isClicked = true
                        tryAwaitRelease()
                        isClicked = false
                        onClick()
                    }
                )
            }
    )
}