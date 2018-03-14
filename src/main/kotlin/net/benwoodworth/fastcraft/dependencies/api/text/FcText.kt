package net.benwoodworth.fastcraft.dependencies.api.text

interface FcText {

    interface Builder {

        fun setText(text: String): Specific
        //fun setTranslation(translation: String): Specific
        //fun setScore(score: FcTextScore): Specific
        fun setSelector(selector: String): Specific
        //fun setKeybind(keybind: String): Specific

        interface Specific {

            fun build(): FcText

            fun setColor(color: FcTextColor): Specific
            fun setBold(bold: Boolean): Specific
            fun setItalic(italic: Boolean): Specific
            fun setUnderlined(underlined: Boolean): Specific
            fun setStrikeThrough(strikeThrough: Boolean): Specific
            fun setObfuscated(obfuscated: Boolean): Specific
            fun setShiftClickAction(action: FcTextAction.ShiftClick): Specific
            fun setClickAction(action: FcTextAction.Click): Specific
            fun setHoverAction(action: FcTextAction.Hover): Specific
            //            fun addWith(text: FcText?): Specific
            fun addExtra(text: FcText): Specific
        }
    }
}