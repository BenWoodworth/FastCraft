package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JSON
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.`as`

@Suppress("ClassName")
abstract class Bukkit_113_R01_FcText : FcText {

    protected abstract fun getUnformattedText(locale: String): String

    val rawText: String by lazy {
        JSON.stringify(
            Bukkit_113_R01_FcTextJson.serializer(),
            Bukkit_113_R01_FcTextJson(this)
        )
    }

    fun legacyText(locale: String): String {
        val extraLegacy = extra.joinToString("") {
            it.`as`<Bukkit_113_R01_FcText>().legacyText(locale)
        }

        return getUnformattedText(locale) + extraLegacy
    }
}
