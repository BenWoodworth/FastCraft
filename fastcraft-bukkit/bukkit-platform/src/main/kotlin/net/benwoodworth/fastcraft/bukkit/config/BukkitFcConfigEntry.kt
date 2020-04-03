package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.Configuration

interface BukkitFcConfigEntry : FcConfigEntry {
    val config: Configuration
    val parentEntry: FcConfigEntry?

    companion object {
        val FcConfigEntry.config: Configuration
            get() = (this as BukkitFcConfigEntry).config

        val FcConfigEntry.parentEntry: FcConfigEntry?
            get() = (this as BukkitFcConfigEntry).parentEntry
    }
}
