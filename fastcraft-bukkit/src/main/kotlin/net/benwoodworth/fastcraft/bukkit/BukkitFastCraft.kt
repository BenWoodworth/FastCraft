package net.benwoodworth.fastcraft.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.FastCraft
import net.benwoodworth.fastcraft.FastCraftFactory
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bstats.bukkit.MetricsLite
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level
import javax.inject.Singleton

@Suppress("unused")
class BukkitFastCraft : JavaPlugin() {
    private var fastCraft: FastCraft? = null

    override fun onEnable() {
        checkBukkitVersion()

        val factory = DaggerBukkitFastCraft_Factory.builder()
            .bukkitFastCraftModule(BukkitFastCraftModule(this))
            .build()

        fastCraft = factory.createFastCraft()

        MetricsLite(this)

        // Initial iteration can be slow, so do it now instead of when FastCraft is opened
        server.recipeIterator().forEach { _ -> }
    }

    override fun onDisable() {
        fastCraft!!.disable()
        fastCraft = null
    }

    override fun reloadConfig() {
        fastCraft!!.reloadConfig()
    }

    private fun checkBukkitVersion() {
        val minSupported = BukkitVersion.parse("1.7.5")

        val versionStr = server.bukkitVersion
        val version = BukkitVersion.parseOrNull(versionStr)

        when {
            version == null -> {
                logger.log(Level.WARNING, "Bukkit API $versionStr could not be parsed, and may not be supported.")
            }
            version < minSupported -> {
                logger.log(Level.SEVERE, "Not compatible with Bukkit API $versionStr.")
            }
        }
    }

    @Singleton
    @Component(modules = [BukkitFastCraftModule::class])
    interface Factory : FastCraftFactory
}
