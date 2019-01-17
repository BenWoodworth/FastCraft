package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

class BukkitFcText_1_13_00_R01(
    val text: String? = null,
    val translate: FcTranslatable? = null,

    val color: FcTextColor? = null,
    val bold: Boolean? = null,
    val italic: Boolean? = null,
    val underline: Boolean? = null,
    val strikethrough: Boolean? = null,
    val obfuscate: Boolean? = null,

    val extra: List<FcText> = emptyList()
) : BukkitFcText
