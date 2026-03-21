package com.hiosdra.smryshare.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Displays an overlay tutorial that guides users through the app's features.
 * Shows one step at a time with navigation controls and a skip option.
 *
 * @param currentStep The current step being displayed
 * @param totalSteps The total number of steps in the tutorial
 * @param onNextStep Callback when the user taps "Next"
 * @param onPreviousStep Callback when the user taps "Previous"
 * @param onSkip Callback when the user taps "Skip"
 * @param onFinish Callback when the user completes the tutorial
 */
@Composable
fun OnboardingOverlay(
    currentStep: OnboardingStep,
    totalSteps: Int,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onSkip: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                // Consume clicks to prevent touch fall-through to underlying UI
            },
        contentAlignment = Alignment.Center
    ) {
        OnboardingCard(
            currentStep = currentStep,
            totalSteps = totalSteps,
            onNextStep = onNextStep,
            onPreviousStep = onPreviousStep,
            onSkip = onSkip,
            onFinish = onFinish
        )
    }
}

/**
 * The card component that displays the tutorial content and controls.
 */
@Composable
private fun OnboardingCard(
    currentStep: OnboardingStep,
    totalSteps: Int,
    onNextStep: () -> Unit,
    onPreviousStep: () -> Unit,
    onSkip: () -> Unit,
    onFinish: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Header with step number and skip button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepIndicator(
                    currentStep = currentStep.stepNumber,
                    totalSteps = totalSteps
                )

                Spacer(modifier = Modifier.weight(1f))

                // Only show skip button if not on the last step
                if (currentStep.stepNumber < totalSteps) {
                    TextButton(onClick = onSkip) {
                        Text(
                            text = "Skip",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step number badge
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentStep.stepNumber.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = currentStep.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = currentStep.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )

            // Highlight area hint (if available)
            currentStep.highlightArea?.let { highlight ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Look for: ${highlight.targetDescription}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous button (only show if not on first step)
                if (currentStep.stepNumber > 1) {
                    OutlinedButton(
                        onClick = onPreviousStep,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Previous")
                    }

                    Spacer(modifier = Modifier.width(12.dp))
                }

                // Next or Finish button
                Button(
                    onClick = if (currentStep.stepNumber == totalSteps) onFinish else onNextStep,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (currentStep.stepNumber == totalSteps) "Get Started" else "Next"
                    )
                }
            }
        }
    }
}

/**
 * Displays the current step progress (e.g., "Step 1 of 5").
 */
@Composable
private fun StepIndicator(
    currentStep: Int,
    totalSteps: Int
) {
    Text(
        text = "Step $currentStep of $totalSteps",
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
