package net.benwoodworth.fastcraft.platform.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.platform.api.config.FcConfigManager
import net.benwoodworth.fastcraft.platform.api.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.api.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.platform.api.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.platform.api.event.FcListener
import net.benwoodworth.fastcraft.platform.api.gui.GuiFactory
import net.benwoodworth.fastcraft.platform.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.api.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.api.server.FcPluginRegistry
import net.benwoodworth.fastcraft.platform.api.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.api.text.FcTextColor
import net.benwoodworth.fastcraft.platform.bukkit.config.BukkitFcConfigManager
import net.benwoodworth.fastcraft.platform.bukkit.event.BukkitFcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.bukkit.event.BukkitFcEventPluginDisable
import net.benwoodworth.fastcraft.platform.bukkit.event.BukkitFcEventPluginEnable
import net.benwoodworth.fastcraft.platform.bukkit.gui.BukkitGuiFactory
import net.benwoodworth.fastcraft.platform.bukkit.item.BukkitFcItemBuilder
import net.benwoodworth.fastcraft.platform.bukkit.item.BukkitFcItemTypes
import net.benwoodworth.fastcraft.platform.bukkit.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.platform.bukkit.recipe.BukkitFcRecipeProvider
import net.benwoodworth.fastcraft.platform.bukkit.server.BukkitFcPluginRegistry
import net.benwoodworth.fastcraft.platform.bukkit.server.BukkitFcTaskBuilder
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcTextColor
import org.bukkit.Server
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent
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
    fun configManager(dep: BukkitFcConfigManager): FcConfigManager = dep

    @Provides
    fun server(): Server = plugin.server

    @Provides
    @Singleton
    fun listenerPlayerJoin(server: Server): FcListener<FcEventPlayerJoin> {
        return FcListener<FcEventPlayerJoin>().also { listener ->
            server.pluginManager.registerEvents(
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
    fun listenerPluginDisable(server: Server): FcListener<FcEventPluginDisable> {
        return FcListener<FcEventPluginDisable>().also { listener ->
            server.pluginManager.registerEvents(
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
    fun listenerPluginEnable(server: Server): FcListener<FcEventPluginEnable> {
        return FcListener<FcEventPluginEnable>().also { listener ->
            server.pluginManager.registerEvents(
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
    fun itemTypeFactory(dep: BukkitFcItemTypes): FcItemTypes = dep

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
    fun textFactory(dep: BukkitFcText.Factory): FcText.Factory = dep

    @Provides
    @Singleton
    fun textColorRegistry(dep: BukkitFcTextColor.Catalog): FcTextColor.Catalog = dep
}