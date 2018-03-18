package net.benwoodworth.fastcraft.implementations.bukkit.text.spigot

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.dependencies.text.FcTextColor
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent

class BukkitFcText_Spigot(
        override val base: BaseComponent
) : Adapter<BaseComponent>(), FcText {

    class Factory : FcText.Factory {

        override fun buildText(text: String) = Builder(TextComponent(text))
    }

    class Builder(
            private val base: BaseComponent
    ) : BukkitFcText.Builder {

        override fun build(): FcText {
            return BukkitFcText_Spigot(base)
        }

        override fun setColor(color: FcTextColor) = also {
            base.color = (color as BukkitFcTextColor_Spigot).base
        }

        override fun setBold(bold: Boolean) = also {
            base.isBold = bold
        }

        override fun setItalic(italic: Boolean) = also {
            base.isItalic = italic
        }

        override fun setUnderlined(underlined: Boolean) = also {
            base.isUnderlined = underlined
        }

        override fun setStrikeThrough(strikeThrough: Boolean) = also {
            base.isStrikethrough = strikeThrough
        }

        override fun setObfuscated(obfuscated: Boolean) = also {
            base.isObfuscated = obfuscated
        }

        override fun addExtra(text: FcText) = also {
            base.addExtra((text as BukkitFcText_Spigot).base)
        }
    }
}