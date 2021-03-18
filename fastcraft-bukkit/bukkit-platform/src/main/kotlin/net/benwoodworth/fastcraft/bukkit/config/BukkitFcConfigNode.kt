package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection

interface FcConfigNode_Bukkit : FcConfigNode {
    val config: ConfigurationSection
}

val FcConfigNode.config: ConfigurationSection
    get() = (this as FcConfigNode_Bukkit).config
