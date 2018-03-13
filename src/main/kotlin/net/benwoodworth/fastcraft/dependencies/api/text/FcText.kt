package net.benwoodworth.fastcraft.dependencies.api.text

interface FcText {

    val color: FcTextColor?
    val bold: Boolean?
    val italic: Boolean?
    val underlined: Boolean?
    val strikethrough: Boolean?
    val obfuscated: Boolean?

    val insertion: String?
    // val clickEvent: FcTextClickEvent
    // val hoverEvent: FcTextHoverEvent

    val with: List<FcText?>?
    val extra: List<FcText>?

    interface Simple : FcText {
        val text: String
    }

    interface Translation : FcText {
        val translate: String
    }

    interface Score : FcText {
        val score: String
    }

    interface Selector : FcText {
        val selector: String
    }

    interface Keybind : FcText {
        val keybind: String
    }
}