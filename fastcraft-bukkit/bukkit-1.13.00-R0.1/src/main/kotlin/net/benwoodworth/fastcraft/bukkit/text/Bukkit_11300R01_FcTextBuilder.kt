package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextColor

@Suppress("ClassName")
class Bukkit_11300R01_FcTextBuilder : FcTextBuilder, FcTextBuilder.Typed<FcText> {

    private lateinit var buildType: Class<*>

    internal lateinit var text: String
    internal lateinit var translate: String

    internal var color: FcTextColor? = null
    internal var bold: Boolean? = null
    internal var italic: Boolean? = null
    internal var underline: Boolean? = null
    internal var strikethrough: Boolean? = null
    internal var obfuscate: Boolean? = null
    internal val extra = mutableListOf<FcText>()

    override fun text(value: String): FcTextBuilder.Typed<FcText.Text> {
        text = value
        buildType = FcText.Text::class.java

        @Suppress("UNCHECKED_CAST")
        return this as FcTextBuilder.Typed<FcText.Text>
    }

    override fun translate(value: String): FcTextBuilder.Typed<FcText.Translate> {
        buildType = FcText.Translate::class.java
        translate = value

        @Suppress("UNCHECKED_CAST")
        return this as FcTextBuilder.Typed<FcText.Translate>
    }

    override fun color(value: FcTextColor): FcTextBuilder.Typed<FcText> {
        color = value
        return this
    }

    override fun bold(value: Boolean): FcTextBuilder.Typed<FcText> {
        bold = value
        return this
    }

    override fun italic(value: Boolean): FcTextBuilder.Typed<FcText> {
        italic = value
        return this
    }

    override fun underline(value: Boolean): FcTextBuilder.Typed<FcText> {
        underline = value
        return this
    }

    override fun strikethrough(value: Boolean): FcTextBuilder.Typed<FcText> {
        strikethrough = value
        return this
    }

    override fun obfuscate(value: Boolean): FcTextBuilder.Typed<FcText> {
        obfuscate = value
        return this
    }

    override fun extra(value: FcText): FcTextBuilder.Typed<FcText> {
        extra.add(value)
        return this
    }

    override fun build(): FcText {
        return when (buildType) {
            FcText.Text::class.java ->
                Bukkit_11300R01_FcText.Text(this)

            FcText.Translate::class.java ->
                Bukkit_11300R01_FcText.Translate(this)

            else -> throw IllegalStateException()
        }
    }
}
