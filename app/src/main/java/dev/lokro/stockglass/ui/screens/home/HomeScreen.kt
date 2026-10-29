package dev.lokro.stockglass.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.lokro.stockglass.ui.theme.StockGlassTheme

@Composable
fun HomeScreen(
    onAddWidget: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    StockGlassTheme {
        androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TopAppBar(
                    title = { Text(text = "StockGlass") },
                    colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
                    ),
                )
                androidx.compose.material3.Text(text = "Übersicht", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
                androidx.compose.material3.Text(text = "Keine Widgets konfiguriert", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
                Button(onClick = onAddWidget) {
                    Text(text = "Widget hinzufügen")
                }
            }
        }
    }
}

class HomeViewModel @javax.inject.Inject constructor() : androidx.lifecycle.ViewModel()
