package com.hiosdra.smryshare

class ArchivePhShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String = "https://archive.ph/newest/$url"
}
