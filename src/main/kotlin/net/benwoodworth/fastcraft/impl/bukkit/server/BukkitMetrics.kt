package net.benwoodworth.fastcraft.impl.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Metrics
import net.benwoodworth.fastcraft.dependencies.server.Plugin

class BukkitMetrics : Metrics {

    override fun start(plugin: Plugin) {
        BukkitBstatsLite((plugin as BukkitPlugin).base)
    }
}