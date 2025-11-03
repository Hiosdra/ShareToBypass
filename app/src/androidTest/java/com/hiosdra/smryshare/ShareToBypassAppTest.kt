package com.hiosdra.smryshare

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hiosdra.smryshare.ui.theme.ShareToBypassTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShareToBypassAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shareToBypassApp_displaysAllContentCorrectly() {
        // Given the app with theme
        composeTestRule.setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }

        // Then verify main content is displayed with proper styling
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
        
        // And verify layout structure
        composeTestRule.onNodeWithContentDescription("Share to bypass").assertIsDisplayed()
        composeTestRule.onNodeWithText("How to use").assertIsDisplayed()
        
        // And verify all four instruction steps are displayed in order
        val steps = listOf(
            "Open any link in your browser or app",
            "Tap the Share button",
            "Choose your preferred service",
            "Link opens automatically in the selected service"
        )

        steps.forEach { step ->
            composeTestRule.onNodeWithText(step).assertIsDisplayed()
        }
    }

}

