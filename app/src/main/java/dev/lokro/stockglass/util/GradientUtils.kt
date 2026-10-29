package dev.lokro.stockglass.util

object GradientUtils {
    fun blend(startColor: Int, endColor: Int, fraction: Float): Int {
        val startAlpha = (startColor shr 24) and 0xFF
        val startRed = (startColor shr 16) and 0xFF
        val startGreen = (startColor shr 8) and 0xFF
        val startBlue = startColor and 0xFF

        val endAlpha = (endColor shr 24) and 0xFF
        val endRed = (endColor shr 16) and 0xFF
        val endGreen = (endColor shr 8) and 0xFF
        val endBlue = endColor and 0xFF

        val f = fraction.coerceIn(0f, 1f)
        val alpha = (startAlpha + (endAlpha - startAlpha) * f).toInt()
        val red = (startRed + (endRed - startRed) * f).toInt()
        val green = (startGreen + (endGreen - startGreen) * f).toInt()
        val blue = (startBlue + (endBlue - startBlue) * f).toInt()

        return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }
}
