package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextStyle
import javax.inject.Inject

/**
 * Bukkit implementation of [FcTextBuilder].
 */
class BukkitFcTextBuilder @Inject constructor(
) : FcTextBuilder {

    private var text: String = ""
    private var extra = mutableListOf<FcText>()
    private var style: FcTextStyle? = null

    override fun build(): FcText {
        val builder = StringBuilder()

        style?.run {
            builder.append((color as BukkitFcTextColor).code)

            builder.takeIf { obfuscated }?.append("\u00A7k")
            builder.takeIf { bold }?.append("\u00A7l")
            builder.takeIf { strikeThrough }?.append("\u00A7m")
            builder.takeIf { underlined }?.append("\u00A7n")
            builder.takeIf { italic }?.append("\u00A7o")
        }

        builder.append(text)

        extra.forEach {
            builder.append((it as BukkitFcText).text)
        }

        return BukkitFcText(builder.toString())
    }

    override fun text(text: String) = also {
        this.text = text
    }

    override fun addExtra(vararg extra: FcText) = also {
        this.extra.addAll(extra)
    }

    override fun textStyle(style: FcTextStyle) = also {
        this.style = style
    }
}