package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import javax.inject.Inject

class BukkitFcTextFactory_1_13_00_R01 @Inject constructor(
) : BukkitFcTextFactory {

    override fun createFcText(
        text: String?,
        translate: String?,
        color: FcTextColor?,
        bold: Boolean?,
        italic: Boolean?,
        underline: Boolean?,
        strikethrough: Boolean?,
        obfuscate: Boolean?,
        extra: List<FcText>
    ): FcText {
        return BukkitFcText_1_13_00_R01(
            text = text,
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

    override fun createFcText(
        text: String,
        color: FcTextColor?,
        bold: Boolean?,
        italic: Boolean?,
        underline: Boolean?,
        strikethrough: Boolean?,
        obfuscate: Boolean?,
        extra: List<FcText>
    ): FcText {
        return BukkitFcText_1_13_00_R01(
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

    override fun createFcText(legacy: String): FcText {
        return BukkitFcText_1_13_00_R01(
            legacy = legacy
        )
    }
}
