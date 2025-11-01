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

    @Test
    fun homeScreen_displaysAllComponents() {
        // Given the home screen
        composeTestRule.setContent {
            ShareToBypassTheme {
                HomeScreen()
            }
        }

        // Then verify all main components are displayed
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
        composeTestRule.onNodeWithText("Share links to smry.ai or use paywall removers").assertIsDisplayed()
        composeTestRule.onNodeWithText("How to use").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysInstructions() {
        // Given the home screen
        composeTestRule.setContent {
            ShareToBypassTheme {
                HomeScreen()
            }
        }

        // Then verify all instruction steps are visible
        composeTestRule.onNodeWithText("Open any link in your browser or app").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tap the Share button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Choose your preferred service").assertIsDisplayed()
        composeTestRule.onNodeWithText("Link opens automatically in the selected service").assertIsDisplayed()
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

    @Test
    fun instructionStep_displaysMultipleSteps() {
        // Given multiple instruction steps
        composeTestRule.setContent {
            ShareToBypassTheme {
                HomeScreen()
            }
        }

        // Then verify each step number is displayed
        for (i in 1..4) {
            composeTestRule.onNodeWithText(i.toString()).assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_displaysSecurityMessage() {
        // Given the home screen
        composeTestRule.setContent {
            ShareToBypassTheme {
                HomeScreen()
            }
        }

        // Then verify security message is displayed
        composeTestRule.onNodeWithText("Links are opened in a secure Chrome Custom Tab")
            .assertIsDisplayed()
    }
}

