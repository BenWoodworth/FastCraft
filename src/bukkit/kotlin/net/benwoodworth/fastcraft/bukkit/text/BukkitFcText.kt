package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import java.util.*

sealed class BukkitFcText : FcText {
    private companion object {
        val formattingCodeRegex = Regex("§.?")

        fun String.toPlaintext(): String {
            return this.replace(formattingCodeRegex, "")
        }

        fun StringBuilder.appendPlaintext(text: FcText) {
            text as BukkitFcText
            when (text) {
                is Legacy -> {
                    append(text.legacyText.toPlaintext())
                }
                is Component -> {
                    when (text) {
                        is Component.Text -> {
                            append(text.text.toPlaintext())
                        }
                        is Component.Translate -> {
                            append('[')
                            append(text.translate)
                            append(']')
                        }
                    }

                    text.extra.forEach {
                        appendPlaintext(it)
                    }
                }
            }
        }
    }

    override fun toPlaintext(): String {
        return buildString {
            appendPlaintext(this@BukkitFcText)
        }
    }

    class Legacy(
        val legacyText: String
    ) : BukkitFcText() {
        override fun equals(other: Any?): Boolean {
            return other is Legacy && legacyText == other.legacyText
        }

        override fun hashCode(): Int {
            return legacyText.hashCode()
        }

        override fun toPlaintext(): String {
            return legacyText.replace(formattingCodeRegex, "")
        }
    }

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
        ) : Component() {
            override fun equals(other: Any?): Boolean {
                return other is Text &&
                        text == other.text &&
                        color == other.color &&
                        bold == other.bold &&
                        italic == other.italic &&
                        underline == other.underline &&
                        strikethrough == other.strikethrough &&
                        obfuscate == other.obfuscate &&
                        extra == other.extra
            }

            override fun hashCode(): Int {
                return Objects.hash(
                    text,
                    color,
                    bold,
                    italic,
                    underline,
                    strikethrough,
                    obfuscate
                )
            }
        }

        class Translate(
            val translate: String,
            override val color: FcTextColor? = null,
            override val bold: Boolean? = null,
            override val italic: Boolean? = null,
            override val underline: Boolean? = null,
            override val strikethrough: Boolean? = null,
            override val obfuscate: Boolean? = null,
            override val extra: List<FcText> = emptyList()
        ) : Component() {
            override fun equals(other: Any?): Boolean {
                return other is Translate &&
                        translate == other.translate &&
                        color == other.color &&
                        bold == other.bold &&
                        italic == other.italic &&
                        underline == other.underline &&
                        strikethrough == other.strikethrough &&
                        obfuscate == other.obfuscate &&
                        extra == other.extra
            }

            override fun hashCode(): Int {
                return Objects.hash(
                    translate,
                    color,
                    bold,
                    italic,
                    underline,
                    strikethrough,
                    obfuscate
                )
            }
        }
    }
}
