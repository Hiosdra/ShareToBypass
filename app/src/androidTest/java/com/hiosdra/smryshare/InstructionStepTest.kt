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
class InstructionStepTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun instructionStep_rendersNumberAndText() {
        composeTestRule.setContent {
            ShareToBypassTheme {
                InstructionStep(number = "1", text = "First step")
            }
        }

        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onNodeWithText("First step").assertIsDisplayed()
    }

    @Test
    fun instructionStep_rendersWithLargeNumber() {
        composeTestRule.setContent {
            ShareToBypassTheme {
                InstructionStep(number = "99", text = "Step with large number")
            }
        }

        composeTestRule.onNodeWithText("99").assertIsDisplayed()
        composeTestRule.onNodeWithText("Step with large number").assertIsDisplayed()
    }

    @Test
    fun instructionStep_rendersWithLongText() {
        composeTestRule.setContent {
            ShareToBypassTheme {
                InstructionStep(number = "3", text = "Third step with longer text content")
            }
        }

        composeTestRule.onNodeWithText("3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Third step with longer text content").assertIsDisplayed()
    }
}

