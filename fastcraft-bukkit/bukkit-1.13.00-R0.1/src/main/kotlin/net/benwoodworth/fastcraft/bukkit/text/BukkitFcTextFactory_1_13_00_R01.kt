package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextFactory_1_13_00_R01 @Inject constructor(
) : BukkitFcTextFactory {

    override fun FcText(
        text: String,
        color: FcTextColor?,
        bold: Boolean?,
        italic: Boolean?,
        underline: Boolean?,
        strikethrough: Boolean?,
        obfuscate: Boolean?,
        extra: List<FcText>?
    ): FcText {
        return BukkitFcText_1_13_00_R01(
            text = text,
            translate = null,
            color = color,
            bold = bold,
            italic = italic,
            underline = underline,
            strikethrough = strikethrough,
            obfuscate = obfuscate,
            extra = extra
        )
    }

    override fun FcText(
        translate: FcTranslatable,
        color: FcTextColor?,
        bold: Boolean?,
        italic: Boolean?,
        underline: Boolean?,
        strikethrough: Boolean?,
        obfuscate: Boolean?,
        extra: List<FcText>?
    ): FcText {
        return BukkitFcText_1_13_00_R01(
            text = null,
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

    override fun FcText(legacyText: FcLegacyText): FcText {
        return FcText(legacyText)
    }
}
