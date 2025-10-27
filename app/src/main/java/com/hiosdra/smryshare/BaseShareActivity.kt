package com.hiosdra.smryshare

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

abstract class BaseShareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleSharedContent()
    }

    private fun handleSharedContent() {
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if (intent.type == "text/plain") {
                    handleSharedText(intent)
                }
            }
        }

        // Close this activity after handling the share
        finish()
    }

    private fun handleSharedText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedText ->
            val url = extractUrl(sharedText)
            if (url != null) {
                openInCustomTab(buildTargetUrl(url))
            }
        }
    }

    private fun extractUrl(text: String): String? {
        val urlPattern = Regex("""https?://[^\s]+""")
        val matchResult = urlPattern.find(text)
        return matchResult?.value
    }

    private fun openInCustomTab(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()

        customTabsIntent.launchUrl(this, url.toUri())
    }

    /**
     * Build the target URL for the specific service.
     * Subclasses must implement this to define their URL format.
     */
    protected abstract fun buildTargetUrl(url: String): String
}

