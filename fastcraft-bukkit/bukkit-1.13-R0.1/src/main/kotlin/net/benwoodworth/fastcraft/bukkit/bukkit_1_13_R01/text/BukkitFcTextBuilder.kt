package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

class BukkitFcTextBuilder : BukkitFcTextBuilder {

    override var text: String? = null
    override var translate: FcTranslatable? = null

    override var color: FcTextColor? = null
    override var bold: Boolean? = null
    override var italic: Boolean? = null
    override var underline: Boolean? = null
    override var strikethrough: Boolean? = null
    override var obfuscate: Boolean? = null

    override var extra: MutableList<FcText> = mutableListOf()

    override fun build(): FcText {
        return BukkitFcText(
            text,
            translate,
            color,
            bold,
            italic,
            underline,
            strikethrough,
            obfuscate,
            extra.toList()
        )
    }
}
