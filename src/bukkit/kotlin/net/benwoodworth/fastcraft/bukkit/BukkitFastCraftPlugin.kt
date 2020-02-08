package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.FastCraft
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

@Suppress("unused")
class BukkitFastCraftPlugin : JavaPlugin() {
    private var fastCraft: FastCraft? = null

    override fun onEnable() {
        checkBukkitVersion()

        val factory = DaggerBukkitFastCraftFactory_1_15_00_R01.builder()
            .bukkitDaggerModule_1_15_00_R01(BukkitDaggerModule_1_15_00_R01(this))
            .build()

        fastCraft = factory.createFastCraft()
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
        val minSupported = BukkitVersion.parse("1.15")!!
        val minUnsupported = BukkitVersion.parse("1.16")!!

        val versionStr = Bukkit.getBukkitVersion()
        val version = BukkitVersion.parse(versionStr)

        when {
            version == null || version >= minUnsupported -> {
                logger.log(Level.WARNING, "Bukkit API $versionStr may not be supported.")
            }
            version < minSupported -> {
                logger.log(Level.SEVERE, "Not compatible with Bukkit API $versionStr.")
            }
        }
    }
}
