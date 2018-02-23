package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.api.event.FcListener
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemTypeFactory
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColorRegistry
import net.benwoodworth.fastcraft.dependencies.config.FcConfigManager
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.dependencies.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.dependencies.server.FcPluginRegistry
import net.benwoodworth.fastcraft.dependencies.server.FcTaskBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.api.gui.BukkitGuiFactory
import net.benwoodworth.fastcraft.implementations.bukkit.api.item.BukkitFcItemBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.api.item.BukkitFcItemTypeFactory
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcTextBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcTextColorRegistry
import net.benwoodworth.fastcraft.implementations.bukkit.config.BukkitFcConfigManager
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPluginDisable
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPluginEnable
import net.benwoodworth.fastcraft.implementations.bukkit.recipe.BukkitFcRecipeProvider
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitFcPluginRegistry
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitFcTaskBuilder
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent
import java.util.logging.Level
import javax.inject.Singleton

@Module
class BukkitFastCraftModule(
        private val plugin: BukkitFastCraft
) {

    @Provides
    @Singleton
    fun plugin(): BukkitFastCraft = plugin

    @Provides
    @Singleton
    fun bukkitApiVersion(): BukkitApiVersion {
        val versionStr = Bukkit.getBukkitVersion()
        return BukkitApiVersion.parse(versionStr) ?: run {
            plugin.logger.log(Level.SEVERE, "Unsupported Bukkit API version: $versionStr. Assuming latest API.")
            BukkitApiVersion(1000)
        }
    }

    @Provides
    @Singleton
    fun configManager(dep: BukkitFcConfigManager): FcConfigManager = dep

    @Provides
    @Singleton
    fun listenerPlayerJoin(): FcListener<FcEventPlayerJoin> {
        return FcListener<FcEventPlayerJoin>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        @Suppress("UNUSED")
                        fun onPlayerJoin(event: PlayerJoinEvent) {
                            listener.notifyHandlers(BukkitFcEventPlayerJoin(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginDisable(): FcListener<FcEventPluginDisable> {
        return FcListener<FcEventPluginDisable>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        @Suppress("UNUSED")
                        fun onPluginDisable(event: PluginDisableEvent) {
                            listener.notifyHandlers(BukkitFcEventPluginDisable(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginEnable(): FcListener<FcEventPluginEnable> {
        return FcListener<FcEventPluginEnable>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        @Suppress("UNUSED")
                        fun onPluginEnable(event: PluginEnableEvent) {
                            listener.notifyHandlers(BukkitFcEventPluginEnable(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    fun guiBuilder(dep: BukkitGuiFactory): GuiFactory = dep

    @Provides
    fun itemBuilder(dep: BukkitFcItemBuilder): FcItemBuilder = dep

    @Provides
    fun itemTypeFactory(dep: BukkitFcItemTypeFactory): FcItemTypeFactory = dep

    @Provides
    @Singleton
    fun recipeProvider(dep: BukkitFcRecipeProvider): FcRecipeProvider = dep

    @Provides
    @Singleton
    fun playerProvider(dep: BukkitFcPlayerProvider): FcPlayerProvider = dep

    @Provides
    fun taskBuilder(dep: BukkitFcTaskBuilder): FcTaskBuilder = dep

    @Provides
    @Singleton
    fun pluginProvider(dep: BukkitFcPluginRegistry): FcPluginRegistry = dep

    @Provides
    fun textBuilder(dep: BukkitFcTextBuilder): FcTextBuilder = dep

    @Provides
    @Singleton
    fun textColorRegistry(dep: BukkitFcTextColorRegistry): FcTextColorRegistry = dep
}