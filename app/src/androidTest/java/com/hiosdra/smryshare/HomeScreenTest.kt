package com.hiosdra.smryshare

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hiosdra.smryshare.ui.theme.ShareToBypassTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        private const val INSTRUCTION_STEPS_COUNT = 4
    }

    @Test
    fun homeScreen_displaysAllContentCorrectly() {
        composeTestRule.setContent {
            ShareToBypassTheme {
                HomeScreen()
            }
        }

        // Wait for UI to settle
        composeTestRule.waitForIdle()

        // Main components
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
        composeTestRule.onNodeWithText("Share links to smry.ai or use paywall removers").assertIsDisplayed()
        composeTestRule.onNodeWithText("How to use").assertIsDisplayed()

        // Instructions
        val instructionTexts = listOf(
            "Open any link in your browser or app",
            "Tap the Share button",
            "Choose your preferred service",
            "Link opens automatically in the selected service"
        )
        instructionTexts.forEach { text ->
            composeTestRule.onNodeWithText(text).assertIsDisplayed()
        }

        // Step numbers
        for (i in 1..INSTRUCTION_STEPS_COUNT) {
            composeTestRule.onNodeWithText(i.toString()).assertIsDisplayed()
        }

        // Security message - check if exists but don't require it to be displayed (might be below fold)
        composeTestRule.onNodeWithText("Links are opened in a secure Chrome Custom Tab", substring = true)
            .assertExists()
    }

    @Test
    fun instructionStep_displaysNumberAndText() {
        // Given an instruction step
        composeTestRule.setContent {
            ShareToBypassTheme {
                InstructionStep(number = "1", text = "Test instruction")
            }
        }

        // Then verify both number and text are displayed
        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test instruction").assertIsDisplayed()
    }
}

