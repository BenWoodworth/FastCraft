package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection

interface BukkitFcConfigNode : FcConfigNode {
    val configSection: ConfigurationSection
}

val FcConfigNode.section: ConfigurationSection
    get() = (this as BukkitFcConfigNode).configSection
