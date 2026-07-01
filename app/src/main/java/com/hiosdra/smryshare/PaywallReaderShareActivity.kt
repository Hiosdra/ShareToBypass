package com.hiosdra.smryshare

class PaywallReaderShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String =
        "https://paywallreader.com/search?url=${encodeUrl(url)}"
}
