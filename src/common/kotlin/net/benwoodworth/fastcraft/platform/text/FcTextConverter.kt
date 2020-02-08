package net.benwoodworth.fastcraft.platform.text

import java.util.*

interface FcTextConverter {
    fun toLegacy(text: FcText, locale: Locale): String

    fun toPlaintext(text: FcText, locale: Locale): String
}