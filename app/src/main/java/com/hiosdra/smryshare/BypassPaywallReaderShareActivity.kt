package com.hiosdra.smryshare

class BypassPaywallReaderShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String =
        "https://www.bypasspaywallreader.com/?url=${encodeUrl(url)}"
}
