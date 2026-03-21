package com.hiosdra.smryshare

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
        finish()
    }

    private fun handleSharedText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedText ->
            extractUrl(sharedText)?.let { url ->
                openInCustomTab(buildTargetUrl(url))
            }
        }
    }

    private fun extractUrl(text: String): String? =
        Regex("""https?://[^\s]+""").find(text)?.value

    /**
     * Build the target URL for the specific service.
     * Subclasses must implement this to define their URL format.
     */
    internal abstract fun buildTargetUrl(url: String): String

    companion object {
        /**
         * Encodes a URL for use as a query parameter.
         */
        fun encodeUrl(url: String): String = URLEncoder.encode(url, StandardCharsets.UTF_8.name())
    }
}
