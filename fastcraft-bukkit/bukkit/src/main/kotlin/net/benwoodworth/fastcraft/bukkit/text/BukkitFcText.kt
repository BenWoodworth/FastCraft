package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTranslatable


interface BukkitFcText : FcText {

    val text: String?
    val translate: FcTranslatable?

    val color: FcTextColor?
    val bold: Boolean?
    val italic: Boolean?
    val underline: Boolean?
    val strikethrough: Boolean?
    val obfuscate: Boolean?

    val extra: List<FcText>?
}
