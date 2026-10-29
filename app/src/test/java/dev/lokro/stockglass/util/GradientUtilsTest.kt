package dev.lokro.stockglass.util

import com.google.truth.Truth.assertThat
import org.junit.Test

class GradientUtilsTest {

    @Test
    fun blend_returnsMixedColor() {
        val start = 0xFF000000.toInt() // black
        val end = 0xFFFFFFFF.toInt()   // white
        val mid = GradientUtils.blend(start, end, 0.5f)
        assertThat(mid).isEqualTo(0xFF7F7F7F.toInt())
    }

    @Test
    fun blend_fractionZero_returnsStart() {
        val start = 0xFF000000.toInt()
        val end = 0xFFFFFFFF.toInt()
        assertThat(GradientUtils.blend(start, end, 0f)).isEqualTo(start)
    }

    @Test
    fun blend_fractionOne_returnsEnd() {
        val start = 0xFF000000.toInt()
        val end = 0xFFFFFFFF.toInt()
        assertThat(GradientUtils.blend(start, end, 1f)).isEqualTo(end)
    }

    @Test
    fun blend_clampsFraction() {
        val start = 0xFF000000.toInt()
        val end = 0xFFFFFFFF.toInt()
        assertThat(GradientUtils.blend(start, end, -0.5f)).isEqualTo(start)
        assertThat(GradientUtils.blend(start, end, 1.5f)).isEqualTo(end)
    }
}
