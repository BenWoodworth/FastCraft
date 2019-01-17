package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor


interface BukkitFcText : FcText {

    val text: String?
    val translate: FcTranslatable?

    val color: FcTextColor?
    val bold: Boolean?
    val italic: Boolean?
    val underline: Boolean?
    val strikethrough: Boolean?
    val obfuscate: Boolean?

    val extra: List<FcText>
}
