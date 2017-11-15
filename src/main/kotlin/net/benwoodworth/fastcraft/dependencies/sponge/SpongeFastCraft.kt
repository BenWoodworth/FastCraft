package net.benwoodworth.fastcraft.dependencies.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.dependencies.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.dependencies.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.dependencies.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.dependencies.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.dependencies.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.dependencies.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.dependencies.sponge.text.SpongeModuleText
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.plugin.Plugin

/**
 * Sponge implementation of FastCraft.
 */
@Plugin(id = "fastcraft")
class SpongeFastCraft : FastCraftImplementation {

    override lateinit var instance: FastCraft
        private set

    @Listener
    @Suppress("UNUSED_PARAMETER", "DEPRECATION") // TODO Don't suppress deprecation
    fun onPreInit(event: GamePreInitializationEvent) {
        val fastCraftBuilder = DaggerSpongeDependenciesComponent.builder()
                .spongeModuleConfig(SpongeModuleConfig())
                .spongeModuleEvent(SpongeModuleEvent(this))
                .spongeModuleGui(SpongeModuleGui(this))
                .spongeModuleItem(SpongeModuleItem(this))
                .spongeModulePlayer(SpongeModulePlayer())
                .spongeModuleServer(SpongeModuleServer(this))
                .spongeModuleText(SpongeModuleText())

        instance = fastCraftBuilder.build().getFastCraft()
    }
}
