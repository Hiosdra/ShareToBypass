package com.hiosdra.smryshare

class ArchiveButtonsShareActivity : BaseShareActivity() {
    override fun buildTargetUrl(url: String): String =
        "https://www.archivebuttons.com/?url=${encodeUrl(url)}"
}
