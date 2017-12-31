package net.benwoodworth.fastcraft.implementations.sponge

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.DependenciesComponent
import net.benwoodworth.fastcraft.implementations.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.implementations.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.implementations.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.implementations.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.implementations.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeModuleText
import javax.inject.Singleton

/**
 * Sponge implementation of [DependenciesComponent].
 */
@Singleton @Component(modules = arrayOf(
        SpongeModuleConfig::class,
        SpongeModuleEvent::class,
        SpongeModuleGui::class,
        SpongeModuleItem::class,
        SpongeModulePlayer::class,
        SpongeModuleServer::class,
        SpongeModuleText::class
))
interface SpongeDependenciesComponent : DependenciesComponent
