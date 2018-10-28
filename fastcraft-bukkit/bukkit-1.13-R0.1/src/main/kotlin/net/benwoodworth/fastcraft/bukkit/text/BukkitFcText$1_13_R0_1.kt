package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JSON
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.`as`

@Suppress("ClassName")
abstract class `BukkitFcText$1_13_R0_1` : FcText {

    protected abstract fun getUnformattedText(locale: String): String

    val rawText: String by lazy {
        JSON.stringify(`BukkitFcTextJson$1_13_R0_1`(this))
    }

    fun legacyText(locale: String): String {
        val extraLegacy = extra.joinToString("") {
            it.`as`<`BukkitFcText$1_13_R0_1`>().legacyText(locale)
        }

        return getUnformattedText(locale) + extraLegacy
    }
}
