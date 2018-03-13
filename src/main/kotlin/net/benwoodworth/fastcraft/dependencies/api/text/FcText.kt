package net.benwoodworth.fastcraft.dependencies.api.text

interface FcText {

    val color: FcTextColor?
    val bold: Boolean?
    val italic: Boolean?
    val underlined: Boolean?
    val strikeThrough: Boolean?
    val obfuscated: Boolean?

    val insertion: String?
    val clickEvent: FcTextClickEvent?
    val hoverEvent: FcTextHoverEvent?

    val with: List<FcText?>?
    val extra: List<FcText>?

    interface Text : FcText {
        val text: String
    }

    interface Translation : FcText {
        val translate: String
    }

    interface Score : FcText {
        val score: FcTextScore
    }

    interface Selector : FcText {
        val selector: String
    }

    interface Keybind : FcText {
        val keybind: String
    }
}