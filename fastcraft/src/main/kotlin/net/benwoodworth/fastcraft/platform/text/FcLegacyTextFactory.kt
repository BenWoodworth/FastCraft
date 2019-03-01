package net.benwoodworth.fastcraft.platform.text

interface FcLegacyTextFactory {

    fun createFcLegacyText(text: FcText, locale: FcLocale): FcLegacyText
}