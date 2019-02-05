package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcText

@Serializable
class BukkitFcRawText_1_13_00_R01(
    var text: String?,
    var translate: String?,
    var color: String?,
    var bold: Boolean?,
    var italic: Boolean?,
    var underline: Boolean?,
    var strikethrough: Boolean?,
    var obfuscate: Boolean?,
    var extra: List<BukkitFcRawText_1_13_00_R01>?
) : BukkitFcRawText {

    constructor(fcText: FcText) : this(
        text = fcText.bukkit.text,
        translate = fcText.bukkit.translate?.bukkit?.key,
        color = fcText.bukkit.color?.bukkit?.id,
        bold = fcText.bukkit.bold,
        italic = fcText.bukkit.italic,
        underline = fcText.bukkit.underline,
        strikethrough = fcText.bukkit.strikethrough,
        obfuscate = fcText.bukkit.obfuscate,
        extra = fcText.bukkit.extra?.map { BukkitFcRawText_1_13_00_R01(it) }
    )

    override fun toJson(): String {
        return JSON.stringify(BukkitFcRawText_1_13_00_R01.serializer(), this)
    }
}