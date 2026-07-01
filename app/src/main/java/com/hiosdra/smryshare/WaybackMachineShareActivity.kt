package com.hiosdra.smryshare

class WaybackMachineShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String = "https://web.archive.org/web/2/$url"
}
