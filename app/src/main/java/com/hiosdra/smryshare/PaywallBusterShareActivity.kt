package com.hiosdra.smryshare

class PaywallBusterShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String =
        "https://paywallbuster.com/articles?article=${encodeUrl(url)}"
}

