package dev.lokro.stockglass.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.linearGradient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StockGlassLogo(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            color = Color(0xFF0F62FE),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(size)
                .clipToBounds(),
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.InsertChart,
                        contentDescription = "StockGlass Logo",
                        tint = Color(0xFFFFFFFF),
                        size = 24.sp,
                    )
                }
            },
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Surface(
                color = Color(0xFF10B981),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(6.dp),
                modifier = Modifier.size(12.dp),
            )
        }
    }
}

@Composable
fun StockGlassLogoSmall(
    modifier: Modifier = Modifier,
) {
    StockGlassLogo(modifier = modifier, size = 32.dp)
}
