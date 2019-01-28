package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory

interface BukkitFcLegacyTextFactory : FcLegacyTextFactory {

    fun FcLegacyText(legacyText: String): FcLegacyText
}