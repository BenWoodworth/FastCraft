package net.benwoodworth.fastcraft.impl.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.impl.sponge.dependencies.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.impl.sponge.dependencies.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.dependencies.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.impl.sponge.dependencies.item.SpongeModuleItem
import net.benwoodworth.fastcraft.impl.sponge.dependencies.permission.SpongeModulePermission
import net.benwoodworth.fastcraft.impl.sponge.dependencies.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.impl.sponge.dependencies.server.SpongeModuleServer
import net.benwoodworth.fastcraft.impl.sponge.dependencies.text.SpongeModuleText
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
        instance = DaggerSpongeFastCraftComponent.builder()
                .spongeModuleConfig(SpongeModuleConfig())
                .spongeModuleEvent(SpongeModuleEvent(this))
                .spongeModuleGui(SpongeModuleGui(this))
                .spongeModuleItem(SpongeModuleItem())
                .spongeModulePermission(SpongeModulePermission())
                .spongeModulePlayer(SpongeModulePlayer())
                .spongeModuleServer(SpongeModuleServer(this))
                .spongeModuleText(SpongeModuleText())
                .build().getFastCraft()
    }
}
