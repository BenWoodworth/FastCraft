package net.benwoodworth.fastcraft.platform.bukkit.text

import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.api.text.FcTextColor

class `BukkitFcTextTranslation$1_13_R0_1`(
    override val translationKey: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : `BukkitFcText$1_13_R0_1`(), BukkitFcTextTranslation {

    override fun getUnformattedText(locale: String): String {
        return "[$locale/$translationKey]"
    }
}