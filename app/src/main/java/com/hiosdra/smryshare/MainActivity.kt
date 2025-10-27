package com.hiosdra.smryshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hiosdra.smryshare.ui.theme.ShareToBypassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShareToBypassTheme {
                ShareToBypassApp()
            }
        }
    }
}

@Composable
fun ShareToBypassApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeScreen(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // App Logo/Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share to bypass",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // App Title
            Text(
                text = "Share to bypass",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "Share links to smry.ai or use paywall removers",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // How to use card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "How to use",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InstructionStep(
                        number = "1",
                        text = "Open any link in your browser or app"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InstructionStep(
                        number = "2",
                        text = "Tap the Share button"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InstructionStep(
                        number = "3",
                        text = "Choose your preferred service"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    InstructionStep(
                        number = "4",
                        text = "Link opens automatically in the selected service"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info text
            Text(
                text = "Links are opened in a secure Chrome Custom Tab",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun InstructionStep(number: String, text: String) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ShareToBypassTheme {
        HomeScreen()
    }
}
