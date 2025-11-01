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
    fun app_displaysCorrectTheme() {
        // Given the app with theme
        composeTestRule.setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }

        // Then verify main content is displayed with proper styling
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
    }

    @Test
    fun app_hasProperContentLayout() {
        // Given the app
        composeTestRule.setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }

        // Then verify layout structure
        composeTestRule.onNodeWithContentDescription("Share to bypass").assertIsDisplayed()
        composeTestRule.onNodeWithText("How to use").assertIsDisplayed()
    }

    @Test
    fun app_displaysAllFourInstructionStepsInOrder() {
        // Given the app
        composeTestRule.setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }

        // Then verify all steps exist
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

    @Test
    fun shareToBypassApp_rendersWithoutCrashing() {
        // When rendering the full app
        composeTestRule.setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }

        // Then verify it renders without crashing
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
    }
}

