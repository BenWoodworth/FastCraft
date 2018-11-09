package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.json
import net.benwoodworth.fastcraft.util.getAs

internal object Bukkit_11300R01_RawTextConverter {

    fun convert(text: Bukkit_11300R01_FcText): String {
        val rawJson = json {
            text.addRawJson(this)

            text.color
                ?.getAs<Bukkit_11300R01_FcTextColor>()
                ?.let { "color" to it.id }

            text.bold?.let { "bold" to it }
            text.italic?.let { "italic" to it }
            text.underline?.let { "underline" to it }
            text.strikethrough?.let { "strikethrough" to it }
            text.obfuscate?.let { "obfuscate" to it }

            text.extra
                .takeIf { it.any() }
                ?.map { it.getAs<Bukkit_11300R01_FcText.Text>() }
                ?.let { "extra" to it }
        }

        return rawJson.toString()
    }
}