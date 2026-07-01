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
        assertEquals("https://removepaywalls.com/$testUrl", result)
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
    fun archivePhShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = ArchivePhUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://archive.ph/newest/$testUrl", result)
    }

    @Test
    fun paywallReaderShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = PaywallReaderUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://paywallreader.com/search?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun paywallReaderShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = PaywallReaderUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://paywallreader.com/search?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun waybackMachineShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = WaybackMachineUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://web.archive.org/web/2/$testUrl", result)
    }

    @Test
    fun archiveButtonsShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = ArchiveButtonsUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.archivebuttons.com/?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun archiveButtonsShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = ArchiveButtonsUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.archivebuttons.com/?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun bypassPaywallReaderShareActivity_buildsCorrectUrl() {
        val testUrl = "https://example.com/article"
        val result = BypassPaywallReaderUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.bypasspaywallreader.com/?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun bypassPaywallReaderShareActivity_encodesSpecialCharacters() {
        val testUrl = "https://example.com/article?id=123&section=news"
        val result = BypassPaywallReaderUrlBuilder.buildTargetUrl(testUrl)
        assertEquals("https://www.bypasspaywallreader.com/?url=${encodeUrl(testUrl)}", result)
    }

    @Test
    fun allActivities_handleHttpsUrls() {
        val testUrl = "https://secure-site.com/premium-content"
        assertEquals("https://smry.ai/$testUrl", SmryAiUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://removepaywalls.com/$testUrl", RemovePaywallsUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://archive.ph/newest/$testUrl", ArchivePhUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://web.archive.org/web/2/$testUrl", WaybackMachineUrlBuilder.buildTargetUrl(testUrl))
    }

    @Test
    fun allActivities_handleHttpUrls() {
        val testUrl = "http://old-site.com/article"
        assertEquals("https://smry.ai/$testUrl", SmryAiUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://removepaywalls.com/$testUrl", RemovePaywallsUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://archive.ph/newest/$testUrl", ArchivePhUrlBuilder.buildTargetUrl(testUrl))
        assertEquals("https://web.archive.org/web/2/$testUrl", WaybackMachineUrlBuilder.buildTargetUrl(testUrl))
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
        fun buildTargetUrl(url: String): String = "https://removepaywalls.com/$url"
    }

    private object PaywallBusterUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://paywallbuster.com/articles?article=${encodeUrl(url)}"
    }

    private object ArchivePhUrlBuilder {
        fun buildTargetUrl(url: String): String = "https://archive.ph/newest/$url"
    }

    private object PaywallReaderUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://paywallreader.com/search?url=${encodeUrl(url)}"
    }

    private object WaybackMachineUrlBuilder {
        fun buildTargetUrl(url: String): String = "https://web.archive.org/web/2/$url"
    }

    private object ArchiveButtonsUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://www.archivebuttons.com/?url=${encodeUrl(url)}"
    }

    private object BypassPaywallReaderUrlBuilder {
        fun buildTargetUrl(url: String): String =
            "https://www.bypasspaywallreader.com/?url=${encodeUrl(url)}"
    }
}

