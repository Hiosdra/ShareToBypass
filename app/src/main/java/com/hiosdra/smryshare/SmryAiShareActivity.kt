package com.hiosdra.smryshare

class SmryAiShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String = "https://smry.ai/$url"
}
