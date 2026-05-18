package com.example.critter_clicker.ui.theme


import com.example.critter_clicker.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// Set of Material typography styles to start with
val miniSquare = FontFamily(
    Font(R.font.kenney_mini_square)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = miniSquare,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),


    bodyMedium = TextStyle(
        fontFamily = miniSquare
    ),

    bodySmall = TextStyle(
        fontFamily = miniSquare
    ),

    titleLarge = TextStyle(
        fontFamily = miniSquare
    ),

    titleMedium = TextStyle(
        fontFamily = miniSquare
    ),

    headlineLarge = TextStyle(
        fontFamily = miniSquare
    ),

    headlineMedium = TextStyle(
        fontFamily = miniSquare
    ),

    labelLarge = TextStyle(
        fontFamily = miniSquare
    )
)
