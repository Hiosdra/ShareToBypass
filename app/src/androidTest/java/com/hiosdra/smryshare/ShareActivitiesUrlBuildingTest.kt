package com.hiosdra.smryshare

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShareActivitiesUrlBuildingTest {

    @Test
    fun urlEncoding_preservesUrlStructure() {
        val urlWithParams = "https://www.nytimes.com/2024/01/01/world/article.html?param1=value1&param2=value2"

        val encoded = BaseShareActivity.encodeUrl(urlWithParams)

        // Verify that URL encoding happened
        assertTrue(encoded.contains("%3A")) // Encoded :
        assertTrue(encoded.contains("%2F")) // Encoded /
        assertTrue(encoded.contains("%3F")) // Encoded ?
        assertTrue(encoded.contains("%26")) // Encoded &
        assertTrue(encoded.contains("%3D")) // Encoded =
    }

    @Test
    fun urlEncoding_handlesSpaces() {
        val urlWithSpaces = "https://example.com/article with spaces"

        val encoded = BaseShareActivity.encodeUrl(urlWithSpaces)

        assertTrue(encoded.contains("+") || encoded.contains("%20"))
    }

    @Test
    fun urlEncoding_handlesHashSymbol() {
        val urlWithHash = "https://example.com/article#section"

        val encoded = BaseShareActivity.encodeUrl(urlWithHash)

        assertTrue(encoded.contains("%23"))
    }

    @Test
    fun urlEncoding_handlesQueryParameters() {
        val urlWithQuery = "https://example.com/test?key=value"

        val encoded = BaseShareActivity.encodeUrl(urlWithQuery)

        assertTrue(encoded.contains("%3F")) // Encoded ?
        assertTrue(encoded.contains("%3D")) // Encoded =
    }

    @Test
    fun urlEncoding_handlesComplexUrl() {
        val complexUrl = "https://example.com/path?p1=v1&p2=v2#anchor"

        val encoded = BaseShareActivity.encodeUrl(complexUrl)

        // All special characters should be encoded
        assertTrue(encoded.contains("%3A")) // :
        assertTrue(encoded.contains("%2F")) // /
        assertTrue(encoded.contains("%3F")) // ?
        assertTrue(encoded.contains("%3D")) // =
        assertTrue(encoded.contains("%26")) // &
        assertTrue(encoded.contains("%23")) // #
    }
}


