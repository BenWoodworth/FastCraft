package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JsonBuilder
import net.benwoodworth.fastcraft.platform.server.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor

@Suppress("ClassName")
sealed class Bukkit_11300R01_FcText(
    builder: Bukkit_11300R01_FcTextBuilder
) : FcText {

    val color: FcTextColor? = builder.color
    val bold: Boolean? = builder.bold
    val italic: Boolean? = builder.italic
    val underline: Boolean? = builder.underline
    val strikethrough: Boolean? = builder.strikethrough
    val obfuscate: Boolean? = builder.obfuscate
    val extra: List<FcText> = builder.extra

    internal abstract fun addRawJson(jsonBuilder: JsonBuilder)

    internal abstract fun getTextPart(): String

    fun toLegacy(locale: FcLocale): String {
        return Bukkit_11300R01_LegacyTextConverter.convert(this, locale)
    }

    fun toRaw(): String {
        return Bukkit_11300R01_RawTextConverter.convert(this)
    }

    class Text(
        builder: Bukkit_11300R01_FcTextBuilder
    ) : Bukkit_11300R01_FcText(builder), FcText.Text {

        val text: String = builder.text

        override fun addRawJson(jsonBuilder: JsonBuilder) {
            "text" to text
        }

        override fun getTextPart(): String {
            return text
        }
    }

    class Translate(
        builder: Bukkit_11300R01_FcTextBuilder
    ) : Bukkit_11300R01_FcText(builder), FcText.Translate {

        val translate: String = builder.translate

        override fun addRawJson(jsonBuilder: JsonBuilder) {
            "translate" to translate
        }

        override fun getTextPart(): String {
            return "[$translate]" // TODO
        }
    }
}
