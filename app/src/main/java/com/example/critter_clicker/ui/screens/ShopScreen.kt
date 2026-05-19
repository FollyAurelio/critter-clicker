package com.example.critter_clicker.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
    onBuy: () -> Unit
) {

    val costText =
        "${getCookieRepresentation(itemCost).second}${
            getCookieRepresentation(itemCost).first
        }"

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

                Text(
                    text = "Cost: $costText cookies",
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onBuy,
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
    )  {
        //Spoon upgrade button
        val spoonNames = listOf("Bronze Spoon", "Silver Spoon", "Gold Spoon", "Platinum Spoon", "Maxed")
        val spoonCosts = listOf(1L,2L,3L,4L,0L) //TODO : Change this
        val cauldronNames = listOf("Super Spoon", "Ultra Spoon", "Mega Spoon", "Hyper Spoon", "Maxed")
        val cauldronCosts = listOf(1L,2L,3L,4L,0L) //TODO : Change this
        ShopButton(
            spoonNames[gameState.spoonLevel - 1],
            "An even better spoon (it's not actually metal, don't worry).",
            R.drawable.spoon,
            spoonCosts[gameState.spoonLevel - 1],
            gameState.spoonLevel < 5,
            {}
        )
        Spacer(modifier = Modifier.width(12.dp))
        ShopButton(
            cauldronNames[gameState.cauldronLevel - 1],
            "An upgraded cauldron that somehow produces even more delicious cookies (crazy).",
            R.drawable.cauldron,
            cauldronCosts[gameState.cauldronLevel - 1],
            gameState.cauldronLevel < 5,
            {}
        )
        ShopButton(
            "Ball",
            "A Ball. Maybe a monkey would like to play with it",
            R.drawable.ball,
            0,
            true,
            {}
        )
        ShopButton(
            "Feather",
            "A Feather. Maybe a monkey would like to play with it",
            R.drawable.feather,
            0,
            true,
            {}
        )
        ShopButton(
            "Ring",
            "A Ring. You might not believe this... but maybe a monkey would like to play with it",
            R.drawable.ring,
            0,
            true,
            {}
        )
        ShopButton(
            "Charcoal",
            "Some Charcoal. Good for fire, and fireguys",
            R.drawable.coal,
            15,
            true,
            {}
        )
        ShopButton(
            "A Flute",
            "A Ball. Maybe a monkey would like to play with it",
            R.drawable.ball,
            0,
            false,
            {}
        )
    }


}
