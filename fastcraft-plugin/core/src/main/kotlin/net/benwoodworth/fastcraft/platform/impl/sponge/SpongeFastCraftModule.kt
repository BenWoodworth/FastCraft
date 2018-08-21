package net.benwoodworth.fastcraft.platform.impl.sponge

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.platform.event.FcListener
import net.benwoodworth.fastcraft.platform.gui.GuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemTypeFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.config.FcConfigManager
import net.benwoodworth.fastcraft.platform.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.platform.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.server.FcPluginRegistry
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.impl.sponge.gui.SpongeGuiFactory
import net.benwoodworth.fastcraft.platform.impl.sponge.item.SpongeFcItemBuilder
import net.benwoodworth.fastcraft.platform.impl.sponge.item.SpongeFcItemTypeFactory
import net.benwoodworth.fastcraft.platform.impl.sponge.player.SpongeFcPlayerProvider
import net.benwoodworth.fastcraft.platform.impl.sponge.text.SpongeFcText
import net.benwoodworth.fastcraft.platform.impl.sponge.text.SpongeFcTextColor
import net.benwoodworth.fastcraft.platform.impl.sponge.config.SpongeFcConfigManager
import net.benwoodworth.fastcraft.platform.impl.sponge.event.SpongeFcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.impl.sponge.event.SpongeFcEventPluginDisable
import net.benwoodworth.fastcraft.platform.impl.sponge.event.SpongeFcEventPluginEnable
import net.benwoodworth.fastcraft.platform.impl.sponge.recipe.SpongeFcRecipeProvider
import net.benwoodworth.fastcraft.platform.impl.sponge.server.SpongeFcPluginRegistry
import net.benwoodworth.fastcraft.platform.impl.sponge.server.SpongeFcTaskBuilder
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent
import org.spongepowered.api.event.network.ClientConnectionEvent.Join
import javax.inject.Singleton

@Module
class SpongeFastCraftModule(
        private val plugin: SpongeFastCraft
) {

    @Provides
    @Singleton
    fun plugin(): SpongeFastCraft = plugin

    @Provides
    @Singleton
    fun configManager(dep: SpongeFcConfigManager): FcConfigManager = dep

    @Provides
    @Singleton
    fun listenerPlayerJoin(plugin: SpongeFastCraft): FcListener<FcEventPlayerJoin> {
        return FcListener<FcEventPlayerJoin>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    Join::class.java,
                    { listener.notifyHandlers(SpongeFcEventPlayerJoin(it)) }
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginDisable(plugin: SpongeFastCraft): FcListener<FcEventPluginDisable> {
        return FcListener<FcEventPluginDisable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameStoppingEvent::class.java,
                    { listener.notifyHandlers(SpongeFcEventPluginDisable(it)) }
            )
        }
    }

    @Provides
    @Singleton
    fun listenerPluginEnable(plugin: SpongeFastCraft): FcListener<FcEventPluginEnable> {
        return FcListener<FcEventPluginEnable>().also { listener ->
            Sponge.getEventManager().registerListener(plugin,
                    GameInitializationEvent::class.java,
                    { listener.notifyHandlers(SpongeFcEventPluginEnable(it)) }
            )
        }
    }

    @Provides
    fun guiBuilder(dep: SpongeGuiFactory): GuiFactory = dep

    @Provides
    fun itemBuilder(dep: SpongeFcItemBuilder): FcItemBuilder = dep

    @Provides
    fun itemTypeFactory(dep: SpongeFcItemTypeFactory): FcItemTypeFactory = dep

    @Provides
    @Singleton
    fun recipeProvider(dep: SpongeFcRecipeProvider): FcRecipeProvider = dep

    @Provides
    @Singleton
    fun playerProvider(dep: SpongeFcPlayerProvider): FcPlayerProvider = dep

    @Provides
    fun taskBuilder(dep: SpongeFcTaskBuilder): FcTaskBuilder = dep

    @Provides
    @Singleton
    fun pluginProvider(dep: SpongeFcPluginRegistry): FcPluginRegistry = dep

    @Provides
    fun textBuilder(dep: SpongeFcText.Builder): FcText.Builder = dep

    @Provides
    @Singleton
    fun textColorRegistry(dep: SpongeFcTextColor.Catalog): FcTextColor.Catalog = dep
}