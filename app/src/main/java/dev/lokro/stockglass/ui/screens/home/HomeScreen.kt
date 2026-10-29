package dev.lokro.stockglass.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
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
