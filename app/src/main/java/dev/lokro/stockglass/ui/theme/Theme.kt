package dev.lokro.stockglass.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.lokro.stockglass.R
import dev.lokro.stockglass.util.ThemePreference

private val LightScheme = lightColorScheme(
    primary = GlassGreen,
    primaryContainer = GlassGreenContainer,
    onPrimary = OnGlassGreen,
    surface = GlassSurface,
    surfaceVariant = GlassSurfaceVariant,
    onSurface = GlassOnSurface,
    onSurfaceVariant = GlassOnSurfaceVariant,
    background = GlassBackground,
    onBackground = GlassOnSurface,
    outline = GlassOutline,
    error = GlassNegative,
    errorContainer = Color(0xFFFEE2E2),
    onError = Color(0xFFFFFFFF),
    inverseSurface = GlassDarkSurface,
    inverseOnSurface = GlassDarkOnSurface,
    inversePrimary = GlassDarkGreen,
)

private val DarkScheme = darkColorScheme(
    primary = GlassDarkGreen,
    primaryContainer = GlassDarkGreenContainer,
    onPrimary = OnGlassDarkGreen,
    surface = GlassDarkSurface,
    surfaceVariant = GlassDarkSurfaceVariant,
    onSurface = GlassDarkOnSurface,
    onSurfaceVariant = GlassDarkOnSurfaceVariant,
    background = GlassDarkBackground,
    onBackground = GlassDarkOnSurface,
    outline = GlassDarkOutline,
    error = GlassNegative,
    errorContainer = Color(0xFF450A0A),
    onError = Color(0xFFFFFFFF),
    inverseSurface = GlassSurface,
    inverseOnSurface = GlassOnSurface,
    inversePrimary = GlassGreen,
)

@Composable
fun StockGlassTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkScheme else LightScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}

@Composable
fun rememberStockGlassTheme(darkTheme: Boolean): androidx.compose.material3.ColorScheme {
    return if (darkTheme) DarkScheme else LightScheme
}
