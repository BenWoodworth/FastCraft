package net.benwoodworth.fastcraft.platform.bukkit.text

import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.api.text.FcTextColor
import net.benwoodworth.fastcraft.platform.bukkit.bukkitLegacyText
import java.lang.StringBuilder

class `BukkitFcTextString$1_13_R0_1`(
    override val text: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : `BukkitFcText$1_13_R0_1`(), BukkitFcTextString {

    override fun getUnformattedText(locale: String): String {
        return text
    }
}