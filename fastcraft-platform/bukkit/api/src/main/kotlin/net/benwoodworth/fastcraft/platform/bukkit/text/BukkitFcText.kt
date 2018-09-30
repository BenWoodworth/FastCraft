package net.benwoodworth.fastcraft.platform.bukkit.text

import net.benwoodworth.fastcraft.platform.api.text.FcText

interface BukkitFcText : FcText {

    @Suppress("PropertyName")
    val `bukkitLegacyText$1_13_R0_1`: String

    @Suppress("PropertyName")
    val `bukkitRawText$1_13_R0_1`: String
}
