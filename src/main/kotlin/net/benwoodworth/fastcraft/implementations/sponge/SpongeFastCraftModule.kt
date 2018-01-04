package net.benwoodworth.fastcraft.implementations.sponge

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.config.ConfigManager
import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemTypeFactory
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider
import net.benwoodworth.fastcraft.dependencies.server.PluginRegistry
import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.dependencies.text.TextColorRegistry
import net.benwoodworth.fastcraft.implementations.sponge.config.SpongeConfigManager
import net.benwoodworth.fastcraft.implementations.sponge.event.SpongeEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.sponge.event.SpongeEventPluginDisable
import net.benwoodworth.fastcraft.implementations.sponge.event.SpongeEventPluginEnable
import net.benwoodworth.fastcraft.implementations.sponge.gui.SpongeGuiBuilder
import net.benwoodworth.fastcraft.implementations.sponge.item.SpongeItemBuilder
import net.benwoodworth.fastcraft.implementations.sponge.item.SpongeItemTypeFactory
import net.benwoodworth.fastcraft.implementations.sponge.item.recipe.SpongeRecipeProvider
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongePlayerProvider
import net.benwoodworth.fastcraft.implementations.sponge.server.SpongePluginRegistry
import net.benwoodworth.fastcraft.implementations.sponge.server.SpongeTask
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeTextColorRegistry
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent
import org.spongepowered.api.event.network.ClientConnectionEvent.Join
import javax.inject.Singleton
import org.spongepowered.api.text.Text as Sponge_Text

@Module
class SpongeFastCraftModule(
        private val plugin: SpongeFastCraft
) {

    @Provides
    @Singleton
    fun plugin(): SpongeFastCraft = plugin

    @Provides
    @Singleton
    fun configManager(dep: SpongeConfigManager): ConfigManager = dep

    @Provides
    @Singleton
    fun listenerPlayerJoin(plugin: SpongeFastCraft): Listener<EventPlayerJoin> {
        return Listener<EventPlayerJoin>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    Join::class.java,
                    { listener.notifyHandlers(SpongeEventPlayerJoin(it)) }
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginDisable(plugin: SpongeFastCraft): Listener<EventPluginDisable> {
        return Listener<EventPluginDisable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameStoppingEvent::class.java,
                    { listener.notifyHandlers(SpongeEventPluginDisable(it)) }
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginEnable(plugin: SpongeFastCraft): Listener<EventPluginEnable> {
        return Listener<EventPluginEnable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameInitializationEvent::class.java,
                    { listener.notifyHandlers(SpongeEventPluginEnable(it)) }
            )
        }
    }

    @Provides
    fun guiBuilder(dep: SpongeGuiBuilder): GuiBuilder = dep

    @Provides
    fun itemBuilder(dep: SpongeItemBuilder): ItemBuilder = dep

    @Provides
    fun itemTypeFactory(dep: SpongeItemTypeFactory): ItemTypeFactory = dep

    @Provides
    @Singleton
    fun recipeProvider(dep: SpongeRecipeProvider): RecipeProvider = dep

    @Provides
    @Singleton
    fun playerProvider(dep: SpongePlayerProvider): PlayerProvider = dep

    @Provides
    fun taskBuilder(dep: SpongeTask.Builder): Task.Builder = dep

    @Provides
    @Singleton
    fun pluginProvider(dep: SpongePluginRegistry): PluginRegistry = dep

    @Provides
    fun textBuilder(dep: SpongeText.Builder): Text.Builder = dep

    @Provides
    @Singleton
    fun textColorRegistry(dep: SpongeTextColorRegistry): TextColorRegistry = dep
}