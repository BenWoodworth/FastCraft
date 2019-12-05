package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

sealed class BukkitFcText : FcText {
    class Legacy(
        val legacyText: String
    ) : BukkitFcText()

    sealed class Component : BukkitFcText() {
        abstract val color: FcTextColor?
        abstract val bold: Boolean?
        abstract val italic: Boolean?
        abstract val underline: Boolean?
        abstract val strikethrough: Boolean?
        abstract val obfuscate: Boolean?
        abstract val extra: List<FcText>

        class Text(
            val text: String,
            override val color: FcTextColor? = null,
            override val bold: Boolean? = null,
            override val italic: Boolean? = null,
            override val underline: Boolean? = null,
            override val strikethrough: Boolean? = null,
            override val obfuscate: Boolean? = null,
            override val extra: List<FcText> = emptyList()
        ) : Component()

        class Translate(
            val translate: String,
            override val color: FcTextColor? = null,
            override val bold: Boolean? = null,
            override val italic: Boolean? = null,
            override val underline: Boolean? = null,
            override val strikethrough: Boolean? = null,
            override val obfuscate: Boolean? = null,
            override val extra: List<FcText> = emptyList()
        ) : Component()
    }
}

val FcText.bukkit: BukkitFcText
    get() = this as BukkitFcText
