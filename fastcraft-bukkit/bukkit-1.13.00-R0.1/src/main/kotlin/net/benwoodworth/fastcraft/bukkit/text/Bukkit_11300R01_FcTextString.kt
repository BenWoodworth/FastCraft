package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.Serializable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

@Serializable
class Bukkit_11300R01_FcTextString(
    val text: String,
    override val color: FcTextColor?,
    override val bold: Boolean?,
    override val italic: Boolean?,
    override val underline: Boolean?,
    override val strikethrough: Boolean?,
    override val obfuscate: Boolean?,
    override val extra: List<FcText>
) : Bukkit_11300R01_FcText {

    override fun toRawText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toLegacyText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}