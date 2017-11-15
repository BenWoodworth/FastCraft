package net.benwoodworth.fastcraft.dependencies.sponge

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.abstractions.DependenciesComponent
import net.benwoodworth.fastcraft.dependencies.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.dependencies.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.dependencies.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.dependencies.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.dependencies.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.dependencies.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.dependencies.sponge.text.SpongeModuleText
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
