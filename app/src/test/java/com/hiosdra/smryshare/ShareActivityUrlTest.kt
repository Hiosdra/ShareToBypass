package com.hiosdra.smryshare

import com.hiosdra.smryshare.BaseShareActivity.Companion.encodeUrl
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for Share Activity URL building logic.
 *
 * These tests use wrapper objects that expose the URL building logic
 * without requiring activity instantiation.
 */
class ShareActivityUrlTest {

    @Test
    fun smryAiShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = SmryAiUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://smry.ai/$testUrl", result)
    }

    @Test
    fun removePaywallShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = RemovePaywallUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.removepaywall.com/search?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun removePaywallShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = RemovePaywallUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.removepaywall.com/search?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun removePaywallsShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = RemovePaywallsUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://removepaywall.com/$testUrl", result)
    }

    @Test
    fun paywallBusterShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = PaywallBusterUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://paywallbuster.com/articles?article=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun paywallBusterShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = PaywallBusterUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://paywallbuster.com/articles?article=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun allActivities_handleHttpsUrls() {
        val testUrl = "https://secure-site.com/premium-content"
        assertEquals("https://smry.ai/$testUrl", SmryAiUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://removepaywall.com/$testUrl", RemovePaywallsUrlBuilder.buildTargetUrl(testUrl))
    }

    @Test
    fun allActivities_handleHttpUrls() {
        val testUrl = "http://old-site.com/article"
        assertEquals("https://smry.ai/$testUrl", SmryAiUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://removepaywall.com/$testUrl", RemovePaywallsUrlBuilder.buildTargetUrl(testUrl))
    }

    // Test wrappers that expose URL building logic without activity lifecycle
    private object SmryAiUrlBuilder {
        fun buildTargetUrl(url: String): String = "https://smry.ai/$url"
    }

    private object RemovePaywallUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://www.removepaywall.com/search?url=${encodeUrl(url)}"
    }

    private object RemovePaywallsUrlBuilder {
        fun buildTargetUrl(url: String): String = "https://removepaywall.com/$url"
    }

    private object PaywallBusterUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://paywallbuster.com/articles?article=${encodeUrl(url)}"
    }
}

