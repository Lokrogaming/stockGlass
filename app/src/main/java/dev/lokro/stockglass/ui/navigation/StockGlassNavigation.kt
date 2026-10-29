package dev.lokro.stockglass.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.onStartAsState
import dev.lokro.stockglass.ui.screens.apiKey.ApiKeyScreen
import dev.lokro.stockglass.ui.screens.home.HomeScreen
import dev.lokro.stockglass.ui.screens.search.SearchScreen
import dev.lokro.stockglass.ui.screens.settings.SettingsScreen
import dev.lokro.stockglass.ui.screens.widgetlist.WidgetListScreen

object StockGlassNavigation {
    const val RouteHome = "home"
    const val RouteSearch = "search"
    const val RouteSettings = "settings"
    const val RouteApiKey = "apikey"
    const val RouteWidgetList = "widgets"
}

@Composable
fun StockGlassNavHost(
    navController: androidx.navigation.NavHostController = rememberNavController(),
    onApiKeyValidated: () -> Unit,
) {
    NavHost(navController = navController, startDestination = StockGlassNavigation.RouteHome) {
        composable(StockGlassNavigation.RouteHome) {
            HomeScreen(onAddWidget = { navController.navigate(StockGlassNavigation.RouteWidgetList) })
        }
        composable(StockGlassNavigation.RouteSearch) {
            SearchScreen()
        }
        composable(StockGlassNavigation.RouteSettings) {
            SettingsScreen(onOpenApiKey = { navController.navigate(StockGlassNavigation.RouteApiKey) })
        }
        composable(StockGlassNavigation.RouteApiKey) {
            ApiKeyScreen(onValidated = onApiKeyValidated)
        }
        composable(StockGlassNavigation.RouteWidgetList) {
            WidgetListScreen()
        }
    }
}
