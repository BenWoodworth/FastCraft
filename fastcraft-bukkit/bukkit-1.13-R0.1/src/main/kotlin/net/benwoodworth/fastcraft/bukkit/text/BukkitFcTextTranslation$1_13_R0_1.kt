package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

class `BukkitFcTextTranslation$1_13_R0_1`(
    override val translationKey: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : `BukkitFcText$1_13_R0_1`(), net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextTranslation {

    override fun getUnformattedText(locale: String): String {
        return "[$locale/$translationKey]"
    }
}