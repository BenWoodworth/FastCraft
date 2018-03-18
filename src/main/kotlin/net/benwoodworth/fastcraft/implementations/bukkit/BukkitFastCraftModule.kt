package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.event.FcListener
import net.benwoodworth.fastcraft.dependencies.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.FcItemTypeFactory
import net.benwoodworth.fastcraft.dependencies.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.dependencies.text.FcTextColor
import net.benwoodworth.fastcraft.dependencies.config.FcConfigManager
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.dependencies.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.dependencies.server.FcPluginRegistry
import net.benwoodworth.fastcraft.dependencies.server.FcTaskBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.gui.BukkitGuiFactory
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitFcItemBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitFcItemTypeFactory
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit.BukkitFcTextColor_Bukkit
import net.benwoodworth.fastcraft.implementations.bukkit.config.BukkitFcConfigManager
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPluginDisable
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitFcEventPluginEnable
import net.benwoodworth.fastcraft.implementations.bukkit.recipe.BukkitFcRecipeProvider
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitFcPluginRegistry
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitFcTaskBuilder
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
    fun textFactory(dep: BukkitFcText.Factory): FcText.Factory = dep

    @Provides
    @Singleton
    fun textColorRegistry(dep: BukkitFcTextColor_Bukkit.Catalog): FcTextColor.Catalog = dep
}