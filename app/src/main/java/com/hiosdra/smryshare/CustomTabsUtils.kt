package com.hiosdra.smryshare

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

/**
 * Opens a URL in a Chrome Custom Tab with consistent styling across the app.
 *
 * @param url The URL to open
 */
fun Context.openInCustomTab(url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
        .launchUrl(this, url.toUri())
}
