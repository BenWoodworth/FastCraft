package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

@Suppress("ClassName")
interface Bukkit_11300R01_FcText : FcText {

    val color: FcTextColor?
    val bold: Boolean?
    val italic: Boolean?
    val underline: Boolean?
    val strikethrough: Boolean?
    val obfuscate: Boolean?
    val extra: List<FcText>

    fun toRawText(): String

    fun toLegacyText(): String
}
