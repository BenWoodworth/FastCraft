package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale

class BukkitFcLocale_1_15_00_R01(
    override val localeId: String
) : BukkitFcLocale {
    override fun equals(other: Any?): Boolean {
        return other is FcLocale && localeId == other.localeId
    }

    override fun hashCode(): Int {
        return localeId.hashCode()
    }
}
