package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JsonBuilder
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

class Bukkit_11300R01_FcTextText(
    val text: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : Bukkit_11300R01_FcText {

    override fun JsonBuilder.addAdditionalJson() {
        "text" to text
    }

    override fun getTextPart(): String {
        return text
    }
}