package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextBuilder : Extensible {

    fun text(value: String): Typed<FcText.Text>

    fun translate(value: String): Typed<FcText.Translate>

    interface Typed<out T : FcText> {

        fun color(value: FcTextColor): Typed<T>

        fun bold(value: Boolean = true): Typed<T>

        fun italic(value: Boolean = true): Typed<T>

        fun underline(value: Boolean = true): Typed<T>

        fun strikethrough(value: Boolean = true): Typed<T>

        fun obfuscate(value: Boolean = true): Typed<T>

        fun extra(value: FcText): Typed<T>

        fun build(): T
    }
}
