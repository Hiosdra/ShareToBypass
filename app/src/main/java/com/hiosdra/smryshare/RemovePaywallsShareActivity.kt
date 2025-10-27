package com.hiosdra.smryshare

class RemovePaywallsShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String = "https://removepaywall.com/$url"
}
