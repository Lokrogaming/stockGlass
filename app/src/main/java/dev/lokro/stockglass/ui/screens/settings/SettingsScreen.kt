package dev.lokro.stockglass.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    onOpenApiKey: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Einstellungen") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            ListItem(
                text = { Text(text = "API-Schlüssel verwalten") },
                onClick = onOpenApiKey,
            )
            ListItem(
                text = { Text(text = "Über StockGlass") },
                onClick = {},
            )
        }
    }
}

class SettingsViewModel @javax.inject.Inject constructor() : androidx.lifecycle.ViewModel()
