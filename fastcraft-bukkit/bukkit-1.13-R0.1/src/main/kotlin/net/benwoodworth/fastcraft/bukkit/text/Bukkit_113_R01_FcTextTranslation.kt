package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextTranslation

class Bukkit_113_R01_FcTextTranslation(
    override val translationKey: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : Bukkit_113_R01_FcText(), FcTextTranslation {

    override fun getUnformattedText(locale: String): String {
        return "[$locale/$translationKey]"
    }
}