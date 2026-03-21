package com.hiosdra.smryshare.onboarding

/**
 * Represents a single step in the onboarding tutorial.
 *
 * @property stepNumber The sequential number of this step (1, 2, 3, etc.)
 * @property title The title/heading for this step
 * @property description The detailed description explaining what to do
 * @property highlightArea Optional area to highlight (for future enhancements with coordinate-based highlighting)
 */
data class OnboardingStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val highlightArea: HighlightArea? = null
)

/**
 * Defines an area to highlight during the tutorial.
 * This can be extended in the future for coordinate-based highlighting.
 *
 * @property targetDescription Human-readable description of what's being highlighted
 */
data class HighlightArea(
    val targetDescription: String
)

/**
 * Provides predefined onboarding steps for the Share to Bypass app.
 */
object OnboardingSteps {
    /**
     * Returns the list of all onboarding steps in sequence.
     */
    fun getAllSteps(): List<OnboardingStep> = listOf(
        OnboardingStep(
            stepNumber = 1,
            title = "Welcome to Share to Bypass!",
            description = "This quick tutorial will show you how to use the app to bypass paywalls and summarize articles. You can skip this tutorial at any time.",
            highlightArea = null
        ),
        OnboardingStep(
            stepNumber = 2,
            title = "Open a Link",
            description = "When you find an article or webpage you want to read, simply open it in your browser or any app.",
            highlightArea = HighlightArea("Browser or any app")
        ),
        OnboardingStep(
            stepNumber = 3,
            title = "Tap the Share Button",
            description = "Look for the Share button (usually in the top-right corner or menu) and tap it.",
            highlightArea = HighlightArea("Share button in your browser")
        ),
        OnboardingStep(
            stepNumber = 4,
            title = "Choose Your Service",
            description = "From the share menu, select one of the available services: Smry.ai for summaries, or a paywall remover to bypass restrictions.",
            highlightArea = HighlightArea("Share menu services")
        ),
        OnboardingStep(
            stepNumber = 5,
            title = "That's It!",
            description = "The link will automatically open in the selected service using a secure Chrome Custom Tab. No need to manually copy-paste links anymore!",
            highlightArea = null
        )
    )
}
