package net.benwoodworth.fastcraft.impl.sponge

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.DependenciesComponent
import net.benwoodworth.fastcraft.impl.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.impl.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.impl.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.impl.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeModuleText
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
