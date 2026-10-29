package dev.lokro.stockglass.util

import android.content.Context
import android.content.SharedPreferences

class ThemePreference(private val context: Context = android.app.Application.getApplicationContext() as Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("stockglass_prefs", Context.MODE_PRIVATE)
    }

    private const val KEY_DARK_THEME = "dark_theme"

    fun isDarkTheme(): Boolean {
        return prefs.getBoolean(KEY_DARK_THEME, false)
    }

    fun setDarkTheme(value: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_THEME, value).apply()
    }
}
