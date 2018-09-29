package net.benwoodworth.fastcraft.platform.api.text

interface FcTextBuilder {

    fun text(text: String): Typed

    fun translation(key: String): Typed

    //fun score(score: FcTextScore): Builder
    //fun selector(selector: String): Builder
    //fun keybind(keybind: String): Builder

    interface Typed : Extra {

        fun color(color: FcTextColor): Typed

        fun color(color: FcTextColors.() -> FcTextColor): Typed

        fun bold(bold: Boolean = true): Typed
        fun italic(italic: Boolean = true): Typed
        fun underline(underline: Boolean = true): Typed
        fun strikethrough(strikethrough: Boolean = true): Typed
        fun obfuscate(obfuscate: Boolean = true): Typed

        //fun setShiftClickAction(action: FcTextAction.ShiftClick): Builder
        //fun setClickAction(action: FcTextAction.Click): Builder
        //fun setHoverAction(action: FcTextAction.Hover): Builder
        //fun addWith(text: FcText?): Builder

    }

    interface Extra {

        fun build(): FcText

        fun addExtra(text: FcText): Extra

        fun addExtra(text: (textBuilder: FcTextBuilder) -> FcText): Extra
    }
}