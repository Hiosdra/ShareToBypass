package com.hiosdra.smryshare.onboarding

import android.content.Context
import android.content.SharedPreferences

/**
 * Manages onboarding preferences, specifically tracking whether the user has completed
 * the initial tutorial walkthrough.
 */
class OnboardingPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    /**
     * Checks if the user has completed the onboarding tutorial.
     * @return true if onboarding is completed, false otherwise
     */
    fun isOnboardingCompleted(): Boolean {
        return preferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    /**
     * Marks the onboarding tutorial as completed.
     */
    fun setOnboardingCompleted() {
        preferences.edit()
            .putBoolean(KEY_ONBOARDING_COMPLETED, true)
            .apply()
    }

    /**
     * Resets the onboarding state (useful for testing or debugging).
     */
    fun resetOnboarding() {
        preferences.edit()
            .putBoolean(KEY_ONBOARDING_COMPLETED, false)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "onboarding_prefs"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }
}
