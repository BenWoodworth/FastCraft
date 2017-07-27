package net.benwoodworth.fastcraft.impl.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.dependencies.FastCraftImplementation
import net.benwoodworth.fastcraft.impl.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.impl.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.gui.SpongeGui
import net.benwoodworth.fastcraft.impl.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.impl.sponge.permission.SpongeModulePermission
import net.benwoodworth.fastcraft.impl.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.impl.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeModuleText
import org.spongepowered.api.Sponge
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
                .spongeModuleItem(SpongeModuleItem(this))
                .spongeModulePermission(SpongeModulePermission())
                .spongeModulePlayer(SpongeModulePlayer())
                .spongeModuleServer(SpongeModuleServer(this))
                .spongeModuleText(SpongeModuleText())
                .build().getFastCraft()
    }
}
