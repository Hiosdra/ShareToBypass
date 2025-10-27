package com.hiosdra.smryshare

import java.net.URLEncoder

class PaywallBusterShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String {
        val encodedUrl = URLEncoder.encode(url, "UTF-8")
        return "https://paywallbuster.com/articles?article=$encodedUrl"
    }
}

