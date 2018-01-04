package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.config.ConfigManager
import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextColorRegistry
import net.benwoodworth.fastcraft.implementations.bukkit.config.BukkitConfigManager
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitEventPluginDisable
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitEventPluginEnable
import net.benwoodworth.fastcraft.implementations.bukkit.gui.BukkitGui
import net.benwoodworth.fastcraft.implementations.bukkit.gui.BukkitGuiBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitItemBuilderProvider
import net.benwoodworth.fastcraft.implementations.bukkit.item.recipe.BukkitRecipeProvider
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitPlayerProvider
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitPlugin
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitTask
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitTextColorRegistry
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
    fun plugin(): BukkitFastCraft {
        return plugin
    }

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
    fun configManager(): ConfigManager {
        return BukkitConfigManager()
    }

    @Provides
    @Singleton
    fun listenerPlayerJoin(): Listener<EventPlayerJoin> {
        return Listener<EventPlayerJoin>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        fun onPlayerJoin(event: PlayerJoinEvent) {
                            listener.notifyHandlers(BukkitEventPlayerJoin(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginDisable(): Listener<EventPluginDisable> {
        return Listener<EventPluginDisable>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        fun onPluginDisable(event: PluginDisableEvent) {
                            listener.notifyHandlers(BukkitEventPluginDisable(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginEnable(): Listener<EventPluginEnable> {
        return Listener<EventPluginEnable>().also { listener ->
            Bukkit.getPluginManager().registerEvents(
                    object : org.bukkit.event.Listener {
                        @EventHandler
                        fun onPluginEnable(event: PluginEnableEvent) {
                            listener.notifyHandlers(BukkitEventPluginEnable(event))
                        }
                    },
                    plugin
            )
        }
    }

    @Provides
    fun guiBuilder(plugin: BukkitFastCraft): GuiBuilder {
        return BukkitGuiBuilder(plugin)
    }

    @Provides
    fun itemBuilder(provider: BukkitItemBuilderProvider): ItemBuilder {
        return provider.get()
    }

    @Provides
    @Singleton
    fun recipeProvider(): RecipeProvider {
        return BukkitRecipeProvider()
    }

    @Provides
    @Singleton
    fun playerProvider(): PlayerProvider {
        return BukkitPlayerProvider()
    }

    @Provides
    fun taskBuilder(plugin: BukkitFastCraft): Task.Builder {
        return BukkitTask.Builder(plugin)
    }

    @Provides
    @Singleton
    fun pluginProvider(): Plugin.Provider {
        return BukkitPlugin.Provider()
    }

    @Provides
    fun textBuilder(): Text.Builder {
        return BukkitText.Builder()
    }

    @Provides
    @Singleton
    fun textColorRegistry(): TextColorRegistry {
        return BukkitTextColorRegistry()
    }
}