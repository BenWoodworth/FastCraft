package net.benwoodworth.fastcraft.dependencies.api.text

interface FcTextBuilder {
    fun setText(text: String): Specific<FcText.Text>
    fun setTranslation(translation: String): Specific<FcText.Translation>
    fun setScore(score: FcTextScore): Specific<FcText.Score>
    fun setSelector(selector: String): Specific<FcText.Selector>
    fun setKeybind(keybind: String): Specific<FcText.Keybind>

    interface Specific<out T : FcText> {

        fun build(): T

        fun setColor(color: FcTextColor): Specific<T>
        fun setBold(bold: Boolean): Specific<T>
        fun setItalic(italic: Boolean): Specific<T>
        fun setUnderlined(underlined: Boolean): Specific<T>
        fun setStrikeThrough(strikeThrough: Boolean): Specific<T>
        fun setObfuscated(obfuscated: Boolean): Specific<T>
        fun setInsertionText(text: String): Specific<T>
        fun setClickEvent(event: FcTextClickEvent): Specific<T>
        fun setHoverEvent(event: FcTextHoverEvent): Specific<T>
        fun addWith(text: FcText?): Specific<T>
        fun addExtra(text: FcText): Specific<T>
    }
}