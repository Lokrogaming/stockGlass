package dev.lokro.stockglass.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Color
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.lokro.stockglass.R

private val DarkColorScheme = dynamicDarkColorScheme(
    primary = Color(0xFF3A86FF),
    surface = Color(0xFF121212),
    background = Color(0xFF121212),
)
private val LightColorScheme = dynamicLightColorScheme(
    primary = Color(0xFF0F62FE),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
)

@Composable
fun StockGlassTheme(
    darkTheme: Boolean = androidx.compose.ui.platform.isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography,
        shapes = androidx.compose.material3.Shapes,
        content = content,
    )
}
