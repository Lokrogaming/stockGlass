package dev.lokro.stockglass.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lokro.stockglass.ui.screens.apiKey.ApiKeyScreen
import dev.lokro.stockglass.ui.screens.home.HomeScreen
import dev.lokro.stockglass.ui.screens.home.WidgetListScreen
import dev.lokro.stockglass.ui.screens.search.SearchScreen
import dev.lokro.stockglass.ui.screens.settings.SettingsScreen
import dev.lokro.stockglass.ui.theme.GlassComponents.PillBottomNavigation
import dev.lokro.stockglass.ui.theme.StockGlassTheme

class MainActivity : ComponentActivity() {

    private val themePreference = dev.lokro.stockglass.util.ThemePreference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDark = themePreference.isDarkTheme()
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContent {
            StockGlassApp()
        }
    }
}

@Composable
fun StockGlassApp() {
    val themePreference = remember { dev.lokro.stockglass.util.ThemePreference() }
    var isDarkTheme by remember { mutableStateOf(themePreference.isDarkTheme()) }
    var selectedTab by remember { mutableStateOf(0) }
    var showApiKey by remember { mutableStateOf(false) }

    StockGlassTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()

        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                HomeScreen(
                    onAddWidget = { selectedTab = 1 },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable("widgets") {
                WidgetListScreen(
                    onAddWidget = { selectedTab = 1 },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable("search") {
                SearchScreen(modifier = Modifier.fillMaxSize())
            }
            composable("settings") {
                SettingsScreen(
                    onApiKeyClicked = { showApiKey = true },
                    onThemeChanged = { isDark ->
                        isDarkTheme = isDark
                        themePreference.setDarkTheme(isDark)
                        if (isDark) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        }
                    },
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable("apikey") {
                ApiKeyScreen(
                    onSave = { showApiKey = false },
                    onCancel = { showApiKey = false },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }

        if (!showApiKey) {
            PillBottomNavigation(
                selectedIndex = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("widgets")
                        2 -> navController.navigate("search")
                        3 -> navController.navigate("settings")
                    }
                },
            )
        }
    }
}
