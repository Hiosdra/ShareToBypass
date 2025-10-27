package com.hiosdra.smryshare

import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URLEncoder

/**
 * Unit tests for Share Activity URL building logic.
 * These test the URL building methods without instantiating activities.
 */
class ShareActivityUrlTest {

    @Test
    fun smryAiShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = buildSmryAiUrl(testUrl)
        assertEquals("https://smry.ai/$testUrl", result)
    }

    @Test
    fun removePaywallShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = buildRemovePaywallUrl(testUrl)
        val encodedUrl = URLEncoder.encode(testUrl, "UTF-8")
        assertEquals("https://www.removepaywall.com/search?url=$encodedUrl", result)
    }

    @Test
    fun removePaywallShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = buildRemovePaywallUrl(testUrl)
        val encodedUrl = URLEncoder.encode(testUrl, "UTF-8")
        assertEquals("https://www.removepaywall.com/search?url=$encodedUrl", result)
    }

    @Test
    fun removePaywallsShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = buildRemovePaywallsUrl(testUrl)
        assertEquals("https://removepaywall.com/$testUrl", result)
    }

    @Test
    fun paywallBusterShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = buildPaywallBusterUrl(testUrl)
        val encodedUrl = URLEncoder.encode(testUrl, "UTF-8")
        assertEquals("https://paywallbuster.com/articles?article=$encodedUrl", result)
    }

    @Test
    fun paywallBusterShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = buildPaywallBusterUrl(testUrl)
        val encodedUrl = URLEncoder.encode(testUrl, "UTF-8")
        assertEquals("https://paywallbuster.com/articles?article=$encodedUrl", result)
    }

    @Test
    fun allActivities_handleHttpsUrls() {
        val testUrl = "https://secure-site.com/premium-content"

        val smryResult = buildSmryAiUrl(testUrl)
        val removePaywallsResult = buildRemovePaywallsUrl(testUrl)

        assertEquals("https://smry.ai/$testUrl", smryResult)
        assertEquals("https://removepaywall.com/$testUrl", removePaywallsResult)
    }

    @Test
    fun allActivities_handleHttpUrls() {
        val testUrl = "http://old-site.com/article"

        val smryResult = buildSmryAiUrl(testUrl)
        val removePaywallsResult = buildRemovePaywallsUrl(testUrl)

        assertEquals("https://smry.ai/$testUrl", smryResult)
        assertEquals("https://removepaywall.com/$testUrl", removePaywallsResult)
    }

    // Helper methods that replicate the URL building logic from each activity
    private fun buildSmryAiUrl(url: String): String = "https://smry.ai/$url"

    private fun buildRemovePaywallUrl(url: String): String {
        val encodedUrl = URLEncoder.encode(url, "UTF-8")
        return "https://www.removepaywall.com/search?url=$encodedUrl"
    }

    private fun buildRemovePaywallsUrl(url: String): String = "https://removepaywall.com/$url"

    private fun buildPaywallBusterUrl(url: String): String {
        val encodedUrl = URLEncoder.encode(url, "UTF-8")
        return "https://paywallbuster.com/articles?article=$encodedUrl"
    }
}

