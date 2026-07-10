package com.example.nearbuymarketplace.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color




private val LightColorScheme = lightColorScheme(
    primary = RichMahogany,
    background = Parchment,
    surface = SoftCream,
    onPrimary = Color.White,
    onBackground = DarkCharcoal,
    onSurface = DarkCharcoal

)

@Composable
fun NearBuyMarketplaceTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}