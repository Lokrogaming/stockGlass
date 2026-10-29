package dev.lokro.stockglass.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lokro.stockglass.ui.theme.GlassCard
import dev.lokro.stockglass.ui.theme.StockGlassLogo

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogo(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), size = 48.dp)

        Text(
            text = "Instrument suchen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "Suchen Sie nach Aktien, ETFs oder anderen Werten.",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
        )

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Symbol oder Name (z. B. AAPL)") },
                    placeholder = { Text(text = "Suchen...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            },
        )

        Text(
            text = "Keine Ergebnisse",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(16.dp),
        )
    }
}
