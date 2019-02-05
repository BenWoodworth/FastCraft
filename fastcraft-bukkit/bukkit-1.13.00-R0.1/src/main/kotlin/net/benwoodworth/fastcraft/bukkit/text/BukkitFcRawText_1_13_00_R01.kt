package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcText

@Serializable
class BukkitFcRawText_1_13_00_R01(
    @Optional var text: String? = null,
    @Optional var translate: String? = null,
    @Optional var color: String? = null,
    @Optional var bold: Boolean? = null,
    @Optional var italic: Boolean? = null,
    @Optional var underline: Boolean? = null,
    @Optional var strikethrough: Boolean? = null,
    @Optional var obfuscate: Boolean? = null,
    @Optional var extra: List<BukkitFcRawText_1_13_00_R01>? = null
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
        extra = fcText.bukkit.extra
            ?.takeIf { it.any() }
            ?.map { BukkitFcRawText_1_13_00_R01(it) }
    )

    override fun toJson(): String {
        return json.stringify(BukkitFcRawText_1_13_00_R01.serializer(), this)
    }

    private companion object {
        val json = Json(encodeDefaults = false)
    }
}