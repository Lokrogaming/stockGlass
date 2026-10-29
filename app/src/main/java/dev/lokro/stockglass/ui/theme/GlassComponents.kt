package dev.lokro.stockglass.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.linearGradient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lokro.stockglass.ui.theme.Color.GlassBackground
import dev.lokro.stockglass.ui.theme.Color.GlassDarkBackground
import dev.lokro.stockglass.ui.theme.Color.GlassDarkSurface
import dev.lokro.stockglass.ui.theme.Color.GlassDarkOutline
import dev.lokro.stockglass.ui.theme.Color.GlassGreen
import dev.lokro.stockglass.ui.theme.Color.GlassGreenContainer
import dev.lokro.stockglass.ui.theme.Color.GlassOutline
import dev.lokro.stockglass.ui.theme.Color.GlassSurface
import dev.lokro.stockglass.ui.theme.Color.GlassSurfaceVariant
import dev.lokro.stockglass.ui.theme.Color.OnGlassGreen

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    elevation: Dp = 0.dp,
) {
    val colorScheme = MaterialTheme.colorScheme
    val surfaceColor = Color(0xFFFFFFFF)
    val outlineColor = Color(0xFFCBD5E1)

    Surface(
        modifier = modifier
            .background(surfaceColor)
            .border(1.dp, outlineColor, RoundedCornerShape(16.dp)),
        color = surfaceColor,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = elevation,
        content = content,
    )
}

@Composable
fun GlassCardWithGradient(
    modifier: Modifier = Modifier,
    startColor: Color = GlassGreen,
    endColor: Color = GlassGreenContainer,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(startColor, endColor),
                ),
            )
            .border(1.dp, Color(0x33FFFFFF), RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            color = Color(0x00000000),
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(20.dp),
            content = content,
        )
    }
}

@Composable
fun PillBottomNavigation(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val isDark = colorScheme.background == Color(0xFF020617.toInt()) || colorScheme.surface == Color(0xFF0F172A.toInt())

    val items = listOf(
        BottomNavItem(0, "Home", androidx.compose.material.icons.Icons.Default.Home),
        BottomNavItem(1, "Widgets", androidx.compose.material.icons.Icons.Default.Widgets),
        BottomNavItem(2, "Suche", androidx.compose.material.icons.Icons.Default.Search),
        BottomNavItem(3, "Einstellungen", androidx.compose.material.icons.Icons.Default.Settings),
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, bottom = 24.dp, top = 8.dp),
    ) {
        Surface(
            color = if (isDark) Color(0xFF0F172A) else Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = if (isDark) Color(0xFF334155) else Color(0xFFCBD5E1),
                    shape = RoundedCornerShape(28.dp),
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            if (isDark) Color(0xFF1E293B) else Color(0xFFF8FAFC),
                            if (isDark) Color(0xFF0F172A) else Color(0xFFFFFFFF),
                        ),
                    ),
                ),
            shape = RoundedCornerShape(28.dp),
        ) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items.forEach { item ->
                    val isSelected = selectedIndex == item.index
                    val itemColor = if (isSelected) GlassGreen else LocalContentColor.current.copy(alpha = 0.5f)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .clickable(
                                role = androidx.compose.foundation.selection.Role.Button,
                                onClick = { onTabSelected(item.index) },
                            ),
                    ) {
                        Surface(
                            color = if (isSelected) GlassGreen else Color(0x00000000),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(20.dp),
                            tonalElevation = if (isSelected) 2.dp else 0.dp,
                        ) {
                            androidx.compose.foundation.layout.Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = if (isSelected) Color(0xFF020617) else itemColor,
                                    size = 20.sp,
                                )
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) Color(0xFF020617) else itemColor,
                                    fontWeight = if (isSelected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class BottomNavItem(
    val index: Int,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
)

@Composable
fun GlassTopBar(
    title: String,
    modifier: Modifier = Modifier,
    showBack: Boolean = false,
    onBack: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = 0.dp,
    ) {
        if (showBack) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                    contentDescription = "Zurück",
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun GlassSectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier,
    )
}

@Composable
fun GlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    singleLine: Boolean = true,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { if (placeholder.isNotEmpty()) Text(text = placeholder) else null },
        singleLine = singleLine,
        modifier = modifier,
        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}
