package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcText
import javax.inject.Inject

class BukkitFcTextConverter_1_13_00_R01 @Inject constructor(
) : BukkitFcTextConverter {

    private val rawFormat = Json(encodeDefaults = false)

    override fun FcText.toRaw(): String {
        return rawFormat.stringify(RawText.serializer(), RawText(this.bukkit))
    }

    @Serializable
    private class RawText(
        @Optional var text: String? = null,
        @Optional var translate: String? = null,
        @Optional var color: String? = null,
        @Optional var bold: Boolean? = null,
        @Optional var italic: Boolean? = null,
        @Optional var underline: Boolean? = null,
        @Optional var strikethrough: Boolean? = null,
        @Optional var obfuscate: Boolean? = null,
        @Optional var extra: List<RawText>? = null
    ) {
        constructor(text: BukkitFcText) : this(
            text = text.text,
            translate = text.translate,
            color = text.color?.bukkit?.id,
            bold = text.bold,
            italic = text.italic,
            underline = text.underline,
            strikethrough = text.strikethrough,
            obfuscate = text.obfuscate,
            extra = text.extra
                ?.takeIf { it.any() }
                ?.map { RawText(it.bukkit) }
        )
    }
}