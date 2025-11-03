package com.hiosdra.smryshare

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseShareActivityTest {

    @Test
    fun baseShareActivity_encodeUrl_handlesSpaces() {
        // Given a URL with spaces
        val testUrl = "https://example.com/article with spaces"

        // When encoding
        val encoded = BaseShareActivity.encodeUrl(testUrl)

        // Then verify spaces are encoded
        assertEquals("https%3A%2F%2Fexample.com%2Farticle+with+spaces", encoded)
    }

    @Test
    fun baseShareActivity_encodeUrl_handlesComplexUrl() {
        // Given a complex URL with query parameters and anchor
        val testUrl = "https://example.com/path?p1=v1&p2=v2#anchor"

        // When encoding
        val encoded = BaseShareActivity.encodeUrl(testUrl)

        // Then verify all special characters are encoded
        assertEquals("https%3A%2F%2Fexample.com%2Fpath%3Fp1%3Dv1%26p2%3Dv2%23anchor", encoded)
    }

    @Test
    fun baseShareActivity_encodeUrl_handlesRealWorldUrl() {
        // Given a real-world URL with parameters (similar to NYTimes example)
        val testUrl = "https://www.nytimes.com/2024/01/01/world/article.html?param1=value1&param2=value2"

        // When encoding
        val encoded = BaseShareActivity.encodeUrl(testUrl)

        // Then verify the URL is properly encoded
        assertEquals("https%3A%2F%2Fwww.nytimes.com%2F2024%2F01%2F01%2Fworld%2Farticle.html%3Fparam1%3Dvalue1%26param2%3Dvalue2", encoded)
    }
}


