package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Module
import dagger.Provides
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin as Bukkit_Plugin
import java.util.logging.Level
import javax.inject.Singleton

@Module
class BukkitModule(
        private val plugin: Bukkit_Plugin
) {

    @Provides
    @Singleton
    fun bukkitApiVersion(): BukkitApiVersion? {
        val versionStr = Bukkit.getBukkitVersion()
        val version = BukkitApiVersion.parse(versionStr)

        if (version == null) {
            plugin.logger.log(
                    Level.SEVERE,
                    "Unsupported Bukkit API version: $versionStr. Please report this as an issue!"
            )
        }

        return version
    }
}
