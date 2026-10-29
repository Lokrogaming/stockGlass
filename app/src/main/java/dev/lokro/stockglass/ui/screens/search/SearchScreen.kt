package dev.lokro.stockglass.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    androidx.compose.material3.Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Instrument suchen") })
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Symbol oder Name (z. B. AAPL)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(onClick = {}) {
                Text(text = "Suchen")
            }
        }
    }
}

class SearchViewModel @javax.inject.Inject constructor() : androidx.lifecycle.ViewModel()
