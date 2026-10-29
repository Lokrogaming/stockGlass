package dev.lokro.stockglass.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lokro.stockglass.ui.theme.GlassCard
import dev.lokro.stockglass.ui.theme.GlassSectionTitle
import dev.lokro.stockglass.ui.theme.StockGlassLogo

@Composable
fun SettingsScreen(
    onApiKeyClicked: () -> Unit,
    onThemeChanged: (Boolean) -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogo(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), size = 48.dp)

        Text(
            text = "Einstellungen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        GlassSectionTitle(text = "Allgemein")

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                ListItem(
                    text = { Text(text = "API-Schlüssel verwalten") },
                    leadingContent = {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Key,
                            contentDescription = "API-Schlüssel",
                        )
                    },
                    onClick = onApiKeyClicked,
                )
                Divider()
                ListItem(
                    text = { Text(text = "Über StockGlass") },
                    leadingContent = {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Info,
                            contentDescription = "Über",
                        )
                    },
                    onClick = {},
                )
            },
        )

        GlassSectionTitle(text = "Design")

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                ListItem(
                    text = { Text(text = "Dunkles Design") },
                    trailingContent = {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = onThemeChanged,
                        )
                    },
                    leadingContent = {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.DarkMode,
                            contentDescription = "Dark Mode",
                        )
                    },
                )
            },
        )

        GlassSectionTitle(text = "Datenquelle")

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                ListItem(
                    text = { Text(text = "Twelve Data") },
                    trailingContent = {
                        Text(
                            text = "Kostenloser API-Zugang",
                            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    leadingContent = {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Cloud,
                            contentDescription = "Datenquelle",
                        )
                    },
                    enabled = false,
                )
            },
        )

        Text(
            text = "Die Kurse stammen von Twelve Data und können verzögert sein. Keine Anlageberatung.",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(16.dp),
        )
    }
}
