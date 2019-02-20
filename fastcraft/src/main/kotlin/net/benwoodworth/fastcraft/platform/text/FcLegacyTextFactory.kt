package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcLocale

interface FcLegacyTextFactory {

    fun createFcLegacyText(text: FcText, locale: FcLocale): FcLegacyText
}