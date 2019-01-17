package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

class BukkitFcText_1_13_00_R01(
    override val text: String? = null,
    override val translate: FcTranslatable? = null,

    override val color: FcTextColor? = null,
    override val bold: Boolean? = null,
    override val italic: Boolean? = null,
    override val underline: Boolean? = null,
    override val strikethrough: Boolean? = null,
    override val obfuscate: Boolean? = null,

    override val extra: List<FcText> = emptyList()
) : BukkitFcText
