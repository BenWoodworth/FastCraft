package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcLocale
import javax.inject.Inject

class BukkitLocalizer_1_13_00_R01 @Inject constructor(
) : BukkitLocalizer {

    override fun localize(key: String, locale: FcLocale): String? {
        return "[${locale.bukkit.localeId}/$key]"
    }
}