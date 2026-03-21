package com.hiosdra.smryshare.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for managing the onboarding tutorial state.
 * Handles navigation between tutorial steps and completion status.
 */
class OnboardingViewModel : ViewModel() {
    private val steps = OnboardingSteps.getAllSteps()

    private val _currentStepIndex = MutableStateFlow(0)
    val currentStepIndex: StateFlow<Int> = _currentStepIndex.asStateFlow()

    private val _isOnboardingActive = MutableStateFlow(false)
    val isOnboardingActive: StateFlow<Boolean> = _isOnboardingActive.asStateFlow()

    /**
     * Gets the current step being displayed.
     */
    val currentStep: OnboardingStep
        get() = steps[_currentStepIndex.value]

    /**
     * Total number of steps in the tutorial.
     */
    val totalSteps: Int = steps.size

    /**
     * Starts the onboarding tutorial from the beginning.
     */
    fun startOnboarding() {
        _currentStepIndex.value = 0
        _isOnboardingActive.value = true
    }

    /**
     * Advances to the next step in the tutorial.
     */
    fun nextStep() {
        if (_currentStepIndex.value < steps.size - 1) {
            _currentStepIndex.value++
        }
    }

    /**
     * Goes back to the previous step in the tutorial.
     */
    fun previousStep() {
        if (_currentStepIndex.value > 0) {
            _currentStepIndex.value--
        }
    }

    /**
     * Skips the tutorial and marks onboarding as inactive.
     */
    fun skipOnboarding() {
        _isOnboardingActive.value = false
        _currentStepIndex.value = 0
    }

    /**
     * Completes the tutorial and marks onboarding as inactive.
     */
    fun finishOnboarding() {
        _isOnboardingActive.value = false
        _currentStepIndex.value = 0
    }
}
