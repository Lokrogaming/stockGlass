package dev.lokro.stockglass.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lokro.stockglass.ui.theme.GlassSectionTitle
import dev.lokro.stockglass.ui.theme.StockGlassLogo

@Composable
fun WidgetListScreen(
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
    ) {
        StockGlassLogo(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), size = 48.dp)

        Text(
            text = "Meine Widgets",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "Noch keine Widgets konfiguriert",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(16.dp),
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onAddWidget,
        ) {
            Text(text = "Widget erstellen")
        }
    }
}
