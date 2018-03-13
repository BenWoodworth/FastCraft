package net.benwoodworth.fastcraft.dependencies.api.text

interface FcTextBuilder {

    fun text(x: String): Specific<FcText.Simple>

    fun translation(x: String): Specific<FcText.Translation>

    fun score(x: String): Specific<FcText.Score>

    fun selector(x: String): Specific<FcText.Selector>

    fun keybind(x: String): Specific<FcText.Keybind>

    interface Specific<out T : FcText> : With<T>, Extra<T> {

        fun setColor(color: FcTextColor): Specific<T>

        fun setBold(bold: Boolean): Specific<T>

        fun setItalic(italic: Boolean): Specific<T>

        fun setUnderlined(underlined: Boolean): Specific<T>

        fun setStrikethrough(strikethrough: Boolean): Specific<T>

        fun setObfuscated(obfuscated: Boolean): Specific<T>

        fun setInsertionText(text: String): Specific<T>
    }

    interface With<out T : FcText> : Extra<T> {

        fun with(text: FcText?): Specific<T>
    }

    interface Extra<out T : FcText> {

        fun extra(text: FcText): Specific<T>

        fun build(): T
    }
}