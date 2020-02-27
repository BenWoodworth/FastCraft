package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.FastCraft
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bstats.bukkit.MetricsLite
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

@Suppress("unused")
class BukkitFastCraftPlugin : JavaPlugin() {
    private var fastCraft: FastCraft? = null

    override fun onEnable() {
        checkBukkitVersion()

        val factory = DaggerBukkitFastCraftFactory.builder()
            .bukkitDaggerModule(BukkitDaggerModule(this))
            .build()

        fastCraft = factory.createFastCraft()

        MetricsLite(this)
    }

    override fun onDisable() {
        fastCraft!!.disable()
        fastCraft = null
    }

    override fun reloadConfig() {
        super.reloadConfig()
        // TODO
    }

    private fun checkBukkitVersion() {
        val minSupported = BukkitVersion.parse("1.8")
        val minUnsupported = BukkitVersion.parse("1.16")

        val versionStr = server.bukkitVersion
        val version = BukkitVersion.parseOrNull(versionStr)

        when {
            version == null -> {
                logger.log(Level.WARNING, "Bukkit API $versionStr could not be parsed, and may not be supported.")
            }
            version >= minUnsupported -> {
                logger.log(Level.WARNING, "Bukkit API $versionStr may not be supported.")
            }
            version < minSupported -> {
                logger.log(Level.SEVERE, "Not compatible with Bukkit API $versionStr.")
            }
        }
    }
}
