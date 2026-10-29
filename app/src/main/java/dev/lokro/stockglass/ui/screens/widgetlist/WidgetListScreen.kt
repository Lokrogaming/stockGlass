package dev.lokro.stockglass.ui.screens.widgetlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WidgetListScreen() {
    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Meine Widgets") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text(text = "Noch keine Widgets")
            Button(onClick = {}) {
                Text(text = "Neues Widget")
            }
        }
    }
}
