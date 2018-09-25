package net.benwoodworth.fastcraft.platform.api.text

interface FcTextBuilder {

    fun text(text: String): Typed

    fun translation(key: String): Typed

    //fun buildTranslation(translation: String): Builder
    //fun buildScore(score: FcTextScore): Builder
    //fun buildSelector(selector: String): Builder
    //fun buildKeybind(keybind: String): Builder

    interface Typed {

        fun build(): FcText

        fun color(color: FcTextColor): Typed

        fun bold(bold: Boolean = true): Typed
        fun italic(italic: Boolean = true): Typed
        fun underline(underline: Boolean = true): Typed
        fun strikethrough(strikethrough: Boolean = true): Typed
        fun obfuscate(obfuscate: Boolean = true): Typed

        //fun setShiftClickAction(action: FcTextAction.ShiftClick): Builder
        //fun setClickAction(action: FcTextAction.Click): Builder
        //fun setHoverAction(action: FcTextAction.Hover): Builder
        //fun addWith(text: FcText?): Builder

        fun addExtra(text: FcText): Typed
    }
}