package com.hiosdra.smryshare.onboarding

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for OnboardingPreferences utility class.
 */
@RunWith(AndroidJUnit4::class)
class OnboardingPreferencesTest {

    private lateinit var context: Context
    private lateinit var onboardingPreferences: OnboardingPreferences

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        onboardingPreferences = OnboardingPreferences(context)
        // Reset preferences before each test
        onboardingPreferences.resetOnboarding()
    }

    @After
    fun cleanup() {
        // Clean up after tests
        onboardingPreferences.resetOnboarding()
    }

    @Test
    fun onboardingNotCompletedByDefault() {
        // Verify that onboarding is not completed by default
        assertFalse(onboardingPreferences.isOnboardingCompleted())
    }

    @Test
    fun setOnboardingCompletedSavesState() {
        // Mark onboarding as completed
        onboardingPreferences.setOnboardingCompleted()

        // Verify it's marked as completed
        assertTrue(onboardingPreferences.isOnboardingCompleted())
    }

    @Test
    fun onboardingStatePersistedAcrossInstances() {
        // Mark onboarding as completed
        onboardingPreferences.setOnboardingCompleted()

        // Create a new instance and verify the state persists
        val newInstance = OnboardingPreferences(context)
        assertTrue(newInstance.isOnboardingCompleted())
    }

    @Test
    fun resetOnboardingClearsState() {
        // Mark onboarding as completed
        onboardingPreferences.setOnboardingCompleted()
        assertTrue(onboardingPreferences.isOnboardingCompleted())

        // Reset onboarding
        onboardingPreferences.resetOnboarding()

        // Verify it's no longer completed
        assertFalse(onboardingPreferences.isOnboardingCompleted())
    }
}
