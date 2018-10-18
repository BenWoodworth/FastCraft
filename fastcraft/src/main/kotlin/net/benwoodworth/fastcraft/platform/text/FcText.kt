package net.benwoodworth.fastcraft.platform.text

interface FcText {

    val color: FcTextColor?

    val bold: Boolean?
    val italic: Boolean?
    val underline: Boolean?
    val strikethrough: Boolean?
    val obfuscate: Boolean?

    val extra: List<FcText>
}
