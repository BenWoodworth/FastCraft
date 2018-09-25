package net.benwoodworth.fastcraft.platform.text

interface FcText {

    interface Factory {

        fun buildText(text: String): Builder
        //fun buildTranslation(translation: String): Builder
        //fun buildScore(score: FcTextScore): Builder
        //fun buildSelector(selector: String): Builder
        //fun buildKeybind(keybind: String): Builder
    }

    interface Builder {

        fun build(): FcText

        fun setColor(color: FcTextColor): Builder
        fun setBold(bold: Boolean): Builder
        fun setItalic(italic: Boolean): Builder
        fun setUnderlined(underlined: Boolean): Builder
        fun setStrikeThrough(strikeThrough: Boolean): Builder
        fun setObfuscated(obfuscated: Boolean): Builder
        //fun setShiftClickAction(action: FcTextAction.ShiftClick): Builder
        //fun setClickAction(action: FcTextAction.Click): Builder
        //fun setHoverAction(action: FcTextAction.Hover): Builder
        //fun addWith(text: FcText?): Builder
        fun addExtra(text: FcText): Builder
    }
}