package com.hiosdra.smryshare

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseShareActivityTest {


    @Test
    fun baseShareActivity_encodeUrl_handlesSpecialCharacters() {
        // Given a URL with special characters
        val testUrl = "https://example.com/test?param=value&other=test#section"

        // When encoding
        val encoded = BaseShareActivity.encodeUrl(testUrl)

        // Then verify special characters are encoded
        assertEquals("https%3A%2F%2Fexample.com%2Ftest%3Fparam%3Dvalue%26other%3Dtest%23section", encoded)
    }

    @Test
    fun baseShareActivity_encodeUrl_handlesSpaces() {
        // Given a URL with spaces
        val testUrl = "https://example.com/article with spaces"

        // When encoding
        val encoded = BaseShareActivity.encodeUrl(testUrl)

        // Then verify spaces are encoded
        assertEquals("https%3A%2F%2Fexample.com%2Farticle+with+spaces", encoded)
    }
}


