package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.json.JSON
import net.benwoodworth.fastcraft.bukkit.bukkitLegacyText

@Suppress("ClassName")
abstract class `BukkitFcText$1_13_R0_1` : net.benwoodworth.fastcraft.bukkit.text.BukkitFcText {

    protected abstract fun getUnformattedText(locale: String): String

    override val `bukkitRawText$1_13_R0_1`: String by lazy {
        JSON.stringify(`BukkitFcTextJson$1_13_R0_1`(this))
    }

    override fun `bukkitLegacyText$1_13_R0_1`(locale: String): String {
        return getUnformattedText(locale) +
                extra.joinToString("") { it.bukkitLegacyText }
    }
}
