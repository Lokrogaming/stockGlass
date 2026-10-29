package dev.lokro.stockglass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "StockGlass v1.0.0 - Coconut\n\nApp läuft!\n\nWidgets können über den Widget-Auswahlbildschirm hinzugefügt werden."
        textView.setTextColor(0xFF000000.toInt())
        textView.setTextSize(20f)
        textView.setPadding(32, 32, 32, 32)
        setContentView(textView)
    }
}
