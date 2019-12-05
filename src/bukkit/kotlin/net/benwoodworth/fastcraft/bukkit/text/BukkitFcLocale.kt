package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale

interface BukkitFcLocale : FcLocale {
    val localeId: String
}

val FcLocale.localeId: String
    get() = (this as BukkitFcLocale).localeId
