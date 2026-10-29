package dev.lokro.stockglass.ui.screens.apiKey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ApiKeyScreen(
    onValidated: () -> Unit,
    viewModel: ApiKeyViewModel = hiltViewModel(),
) {
    var apiKey by remember { mutableStateOf("") }

    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "API-Schlüssel") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text(text = "Geben Sie Ihren Twelve Data API-Schlüssel ein")
            OutlinedTextField(
                value = apiKey,
                onValueChange = { apiKey = it },
                label = { Text(text = "API-Schlüssel") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(onClick = { onValidated() }) {
                Text(text = "Speichern")
            }
        }
    }
}

class ApiKeyViewModel @javax.inject.Inject constructor() : androidx.lifecycle.ViewModel()
