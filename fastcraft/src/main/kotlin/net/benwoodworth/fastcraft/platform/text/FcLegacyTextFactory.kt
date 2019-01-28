package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcLocale

interface FcLegacyTextFactory {

    fun FcLegacyText(text: FcText, locale: FcLocale): FcLegacyText
}