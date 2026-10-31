package dev.lokro.stockglass.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainActivityStartsWithoutCrash() {
        // Prüft, ob die Activity erfolgreich gestartet wurde
        composeTestRule.onRoot().assertIsDisplayed()
    }

    @Test
    fun composeUiRenders() {
        // Prüft, ob der Compose-Inhalt gerendert wurde (Text "StockGlass")
        composeTestRule.onNodeWithText("StockGlass").assertIsDisplayed()
    }
}
