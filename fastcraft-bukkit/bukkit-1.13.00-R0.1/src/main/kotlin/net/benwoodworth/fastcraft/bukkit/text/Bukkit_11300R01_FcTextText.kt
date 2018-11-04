package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JsonBuilder
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

@Suppress("ClassName")
class Bukkit_11300R01_FcTextText(
    val text: String,
    override val color: FcTextColor? = null,
    override val bold: Boolean? = null,
    override val italic: Boolean? = null,
    override val underline: Boolean? = null,
    override val strikethrough: Boolean? = null,
    override val obfuscate: Boolean? = null,
    override val extra: List<FcText> = emptyList()
) : Bukkit_11300R01_FcText {

    override fun JsonBuilder.addAdditionalJson() {
        "text" to text
    }

    override fun getTextPart(): String {
        return text
    }
}