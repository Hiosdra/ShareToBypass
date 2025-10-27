package com.hiosdra.smryshare

import java.net.URLEncoder

class RemovePaywallShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String {
        val encodedUrl = URLEncoder.encode(url, "UTF-8")
        return "https://www.removepaywall.com/search?url=$encodedUrl"
    }
}

