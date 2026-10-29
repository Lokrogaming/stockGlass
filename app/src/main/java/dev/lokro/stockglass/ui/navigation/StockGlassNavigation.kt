package dev.lokro.stockglass.ui.navigation

import dev.lokro.stockglass.ui.screens.home.HomeScreen

object StockGlassNavigation {
    const val RouteHome = "home"
    const val RouteSearch = "search"
    const val RouteSettings = "settings"
    const val RouteApiKey = "apikey"
    const val RouteWidgetList = "widgets"
}

class StockGlassNavHost {
    fun showHome() = HomeScreen()
}
