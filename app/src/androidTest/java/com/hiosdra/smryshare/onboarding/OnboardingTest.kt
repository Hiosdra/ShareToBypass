package com.hiosdra.smryshare.onboarding

import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hiosdra.smryshare.ui.theme.ShareToBypassTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for OnboardingOverlay composable and OnboardingViewModel.
 */
@RunWith(AndroidJUnit4::class)
class OnboardingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onboardingOverlayDisplaysFirstStep() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        composeTestRule.setContent {
            ShareToBypassTheme {
                OnboardingOverlay(
                    currentStep = viewModel.currentStep.value,
                    totalSteps = viewModel.totalSteps,
                    onNextStep = { viewModel.nextStep() },
                    onPreviousStep = { viewModel.previousStep() },
                    onSkip = { viewModel.skipOnboarding() },
                    onFinish = { viewModel.finishOnboarding() }
                )
            }
        }

        // Verify first step content is displayed
        composeTestRule.onNodeWithText("Welcome to Share to Bypass!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Step 1 of 5").assertIsDisplayed()
        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
        composeTestRule.onNodeWithText("Skip").assertIsDisplayed()
    }

    @Test
    fun viewModelStartsAtFirstStep() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        assertEquals(0, viewModel.currentStepIndex.value)
        assertTrue(viewModel.isOnboardingActive.value)
        assertEquals(1, viewModel.currentStep.value.stepNumber)
    }

    @Test
    fun viewModelNavigatesToNextStep() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        viewModel.nextStep()

        assertEquals(1, viewModel.currentStepIndex.value)
        assertEquals(2, viewModel.currentStep.value.stepNumber)
    }

    @Test
    fun viewModelNavigatesToPreviousStep() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()
        viewModel.nextStep()

        viewModel.previousStep()

        assertEquals(0, viewModel.currentStepIndex.value)
        assertEquals(1, viewModel.currentStep.value.stepNumber)
    }

    @Test
    fun viewModelSkipsOnboarding() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        viewModel.skipOnboarding()

        assertFalse(viewModel.isOnboardingActive.value)
    }

    @Test
    fun viewModelFinishesOnboarding() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        viewModel.finishOnboarding()

        assertFalse(viewModel.isOnboardingActive.value)
    }

    @Test
    fun onboardingStepsHaveCorrectCount() {
        val steps = OnboardingSteps.getAllSteps()
        assertEquals(5, steps.size)
    }

    @Test
    fun onboardingStepsHaveSequentialNumbers() {
        val steps = OnboardingSteps.getAllSteps()
        steps.forEachIndexed { index, step ->
            assertEquals(index + 1, step.stepNumber)
        }
    }

    @Test
    fun nextButtonNavigatesToNextStep() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        composeTestRule.setContent {
            ShareToBypassTheme {
                OnboardingOverlay(
                    currentStep = viewModel.currentStep.value,
                    totalSteps = viewModel.totalSteps,
                    onNextStep = { viewModel.nextStep() },
                    onPreviousStep = { viewModel.previousStep() },
                    onSkip = { viewModel.skipOnboarding() },
                    onFinish = { viewModel.finishOnboarding() }
                )
            }
        }

        // Click Next button
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.waitForIdle()

        // Verify we're on step 2
        assertEquals(1, viewModel.currentStepIndex.value)
    }

    @Test
    fun lastStepShowsGetStartedButton() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        // Navigate to last step
        while (viewModel.currentStepIndex.value < viewModel.totalSteps - 1) {
            viewModel.nextStep()
        }

        composeTestRule.setContent {
            ShareToBypassTheme {
                OnboardingOverlay(
                    currentStep = viewModel.currentStep.value,
                    totalSteps = viewModel.totalSteps,
                    onNextStep = { viewModel.nextStep() },
                    onPreviousStep = { viewModel.previousStep() },
                    onSkip = { viewModel.skipOnboarding() },
                    onFinish = { viewModel.finishOnboarding() }
                )
            }
        }

        // Verify "Get Started" button is displayed instead of "Next"
        composeTestRule.onNodeWithText("Get Started").assertIsDisplayed()
    }

    @Test
    fun firstStepDoesNotShowPreviousButton() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()

        composeTestRule.setContent {
            ShareToBypassTheme {
                OnboardingOverlay(
                    currentStep = viewModel.currentStep.value,
                    totalSteps = viewModel.totalSteps,
                    onNextStep = { viewModel.nextStep() },
                    onPreviousStep = { viewModel.previousStep() },
                    onSkip = { viewModel.skipOnboarding() },
                    onFinish = { viewModel.finishOnboarding() }
                )
            }
        }

        // Verify "Previous" button is not displayed on first step
        composeTestRule.onNodeWithText("Previous").assertDoesNotExist()
    }

    @Test
    fun laterStepsShowPreviousButton() {
        val viewModel = OnboardingViewModel()
        viewModel.startOnboarding()
        viewModel.nextStep()

        composeTestRule.setContent {
            ShareToBypassTheme {
                OnboardingOverlay(
                    currentStep = viewModel.currentStep.value,
                    totalSteps = viewModel.totalSteps,
                    onNextStep = { viewModel.nextStep() },
                    onPreviousStep = { viewModel.previousStep() },
                    onSkip = { viewModel.skipOnboarding() },
                    onFinish = { viewModel.finishOnboarding() }
                )
            }
        }

        // Verify "Previous" button is displayed on later steps
        composeTestRule.onNodeWithText("Previous").assertIsDisplayed()
    }
}
