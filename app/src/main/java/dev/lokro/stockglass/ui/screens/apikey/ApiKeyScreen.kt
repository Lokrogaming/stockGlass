package dev.lokro.stockglass.ui.screens.apikey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lokro.stockglass.ui.theme.GlassCard
import dev.lokro.stockglass.ui.theme.StockGlassLogo

@Composable
fun ApiKeyScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var apiKey by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogo(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), size = 48.dp)

        Text(
            text = "API-Schlüssel",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "Geben Sie Ihren Twelve Data API-Schlüssel ein, um echte Kursdaten zu empfangen.",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
        )

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                androidx.compose.material3.TextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    label = { Text(text = "API-Schlüssel") },
                    placeholder = { Text(text = "twelvedata.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            },
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onSave,
            ) {
                Text(text = "Speichern")
            }
            TextButton(
                modifier = Modifier.weight(1f),
                onClick = onCancel,
            ) {
                Text(text = "Abbrechen")
            }
        }
    }
}
