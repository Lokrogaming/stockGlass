package dev.lokro.stockglass.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lokro.stockglass.ui.theme.GlassCard
import dev.lokro.stockglass.ui.theme.GlassSectionTitle
import dev.lokro.stockglass.ui.theme.StockGlassLogo
import dev.lokro.stockglass.ui.theme.Color.GlassPositive
import dev.lokro.stockglass.ui.theme.Color.GlassNegative
import dev.lokro.stockglass.ui.theme.Color.GlassNeutral

@Composable
fun HomeScreen(
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogo(modifier = Modifier.align(Alignment.CenterHorizontally), size = 56.dp)

        Text(
            text = "Willkommen bei StockGlass",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "Aktienkurse immer im Blick – direkt auf Ihrem Startbildschirm.",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        ) {
            GlassCard(
                modifier = Modifier.weight(1f),
                content = {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "+12,4%",
                            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                            color = GlassPositive,
                        )
                        Text(
                            text = "Markt-Übersicht",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
            )
            GlassCard(
                modifier = Modifier.weight(1f),
                content = {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "24",
                            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                            color = GlassNeutral,
                        )
                        Text(
                            text = "Verfolgte Werte",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
            )
            GlassCard(
                modifier = Modifier.weight(1f),
                content = {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "0",
                            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                            color = GlassNegative,
                        )
                        Text(
                            text = "Alerts aktiv",
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
            )
        }

        GlassSectionTitle(text = "Schnellaktionen")

        androidx.compose.material3.Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onAddWidget,
        ) {
            Text(text = "Neues Widget hinzufügen")
        }
    }
}


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogoSmall(modifier = Modifier.align(Alignment.CenterHorizontally))

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
                androidx.compose.material3.TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Symbol oder Name (z. B. AAPL)") },
                    placeholder = { Text(text = "Suchen...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                )
            },
        )

        androidx.compose.material3.Text(
            text = "Keine Ergebnisse",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(16.dp),
        )
    }
}

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
        StockGlassLogoSmall(modifier = Modifier.align(Alignment.CenterHorizontally))

        Text(
            text = "Einstellungen",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )

        GlassSectionTitle(text = "Allgemein")

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                androidx.compose.material3.ListItem(
                    text = { Text(text = "API-Schlüssel verwalten") },
                    leadingContent = {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Key,
                            contentDescription = "API-Schlüssel",
                        )
                    },
                    onClick = onApiKeyClicked,
                )
                androidx.compose.material3.Divider()
                androidx.compose.material3.ListItem(
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
                androidx.compose.material3.ListItem(
                    text = { Text(text = "Dunkles Design") },
                    trailingContent = {
                        androidx.compose.material3.Switch(
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
                androidx.compose.material3.ListItem(
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

        androidx.compose.material3.Text(
            text = "Die Kurse stammen von Twelve Data und können verzögert sein. Keine Anlageberatung.",
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun ApiKeyScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var apiKey by androidx.compose.runtime.remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        StockGlassLogoSmall(modifier = Modifier.align(Alignment.CenterHorizontally))

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
            androidx.compose.material3.Button(
                modifier = Modifier.weight(1f),
                onClick = onSave,
            ) {
                Text(text = "Speichern")
            }
            androidx.compose.material3.TextButton(
                modifier = Modifier.weight(1f),
                onClick = onCancel,
            ) {
                Text(text = "Abbrechen")
            }
        }
    }
}
