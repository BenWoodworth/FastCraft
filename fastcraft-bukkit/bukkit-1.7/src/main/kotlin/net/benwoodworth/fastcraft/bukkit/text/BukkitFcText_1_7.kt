package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcText_1_7 {
    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcText.Factory {
        override fun createFcText(
            text: String,
            color: FcTextColor?,
            bold: Boolean?,
            italic: Boolean?,
            underline: Boolean?,
            strikethrough: Boolean?,
            obfuscate: Boolean?,
            extra: List<FcText>,
        ): FcText {
            return BukkitFcText.Component.Text(
                text = text,
                color = color,
                bold = bold,
                italic = italic,
                underline = underline,
                strikethrough = strikethrough,
                obfuscate = obfuscate,
                extra = extra
            )
        }

        override fun createFcTextTranslate(
            translate: String,
            color: FcTextColor?,
            bold: Boolean?,
            italic: Boolean?,
            underline: Boolean?,
            strikethrough: Boolean?,
            obfuscate: Boolean?,
            extra: List<FcText>,
        ): FcText {
            return BukkitFcText.Component.Translate(
                translate = translate,
                color = color,
                bold = bold,
                italic = italic,
                underline = underline,
                strikethrough = strikethrough,
                obfuscate = obfuscate,
                extra = extra
            )
        }

        override fun createLegacy(legacyText: String): FcText {
            return BukkitFcText.Legacy(legacyText)
        }
    }
}
