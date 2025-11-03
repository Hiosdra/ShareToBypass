package com.hiosdra.smryshare

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appLaunchesSuccessfully() {
        // Verify that the app launches without crashing and displays the title
        composeTestRule.onNodeWithText("Share to bypass").assertIsDisplayed()
    }

    @Test
    fun appDisplaysSubtitle() {
        // Verify the subtitle is displayed
        composeTestRule.onNodeWithText("Share links to smry.ai or use paywall removers")
            .assertIsDisplayed()
    }

    @Test
    fun appDisplaysShareIcon() {
        // Verify the share icon is displayed
        composeTestRule.onNodeWithContentDescription("Share to bypass")
            .assertIsDisplayed()
    }

    @Test
    fun appDisplaysHowToUseSection() {
        // Verify the "How to use" section is displayed
        composeTestRule.onNodeWithText("How to use").assertIsDisplayed()
    }

    @Test
    fun appDisplaysAllInstructionSteps() {
        // Verify all four instruction steps are displayed
        composeTestRule.onNodeWithText("Open any link in your browser or app")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Tap the Share button")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Choose your preferred service")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Link opens automatically in the selected service")
            .assertIsDisplayed()
    }

    @Test
    fun appDisplaysSecurityInfo() {
        // Wait for UI to settle
        composeTestRule.waitForIdle()
        
        // Verify the security information text exists (might be below fold in CI)
        composeTestRule.onNodeWithText("Links are opened in a secure Chrome Custom Tab", substring = true)
            .assertExists()
    }

    @Test
    fun appDisplaysStepNumbers() {
        // Verify that step numbers are displayed
        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()
        composeTestRule.onNodeWithText("3").assertIsDisplayed()
        composeTestRule.onNodeWithText("4").assertIsDisplayed()
    }
}

