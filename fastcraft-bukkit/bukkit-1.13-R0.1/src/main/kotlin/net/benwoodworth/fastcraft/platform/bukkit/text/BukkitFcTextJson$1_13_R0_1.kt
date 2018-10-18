package net.benwoodworth.fastcraft.platform.bukkit.text

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextString
import net.benwoodworth.fastcraft.platform.text.FcTextTranslation
import net.benwoodworth.fastcraft.platform.bukkit.bukkitColorName

@Suppress("unused")
@Serializable
inline class `BukkitFcTextJson$1_13_R0_1`(
    @Transient private val fcText: FcText
) {

    @SerialName("text")
    val text: String?
        get() = (fcText as? FcTextString)?.text

    @SerialName("translate")
    val translate: String?
        get() = (fcText as? FcTextTranslation)?.translationKey

    @SerialName("color")
    val color: String?
        get() = fcText.color?.bukkitColorName

    @SerialName("bold")
    val bold: Boolean?
        get() = fcText.bold

    @SerialName("italic")
    val italic: Boolean?
        get() = fcText.italic

    @SerialName("underline")
    val underline: Boolean?
        get() = fcText.underline

    @SerialName("strikethrough")
    val strikethrough: Boolean?
        get() = fcText.strikethrough

    @SerialName("obfuscated")
    val obfuscated: Boolean?
        get() = fcText.obfuscate

    @SerialName("extra")
    val extra: List<`BukkitFcTextJson$1_13_R0_1`>?
        get() = fcText.extra
            .takeIf { it.any() }
            ?.map { `BukkitFcTextJson$1_13_R0_1`(it) }
}