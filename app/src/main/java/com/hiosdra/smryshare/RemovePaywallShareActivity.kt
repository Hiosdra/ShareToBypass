package com.hiosdra.smryshare

class RemovePaywallShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String =
        "https://www.removepaywall.com/search?url=${encodeUrl(url)}"
}

