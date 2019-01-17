package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.Configuration

interface BukkitFcConfigEntry : FcConfigEntry {

    val config: Configuration
    val parentEntry: BukkitFcConfigEntry?
}
