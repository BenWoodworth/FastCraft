package net.benwoodworth.fastcraft.sponge

import dagger.Component
import net.benwoodworth.fastcraft.core.FastCraftComponent
import net.benwoodworth.fastcraft.sponge.dependencies.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.sponge.dependencies.item.SpongeModuleItem
import net.benwoodworth.fastcraft.sponge.dependencies.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.sponge.dependencies.server.SpongeModuleServer
import net.benwoodworth.fastcraft.sponge.dependencies.text.SpongeModuleText
import javax.inject.Singleton

/**
 * Dagger component for Sponge FastCraft implementation.
 */
@Singleton @Component(modules = arrayOf(
        //SpongeModuleConfig::class,
        SpongeModuleEvent::class,
        //SpongeModuleGui::class,
        SpongeModuleItem::class,
        //SpongeModulePermission::class,
        SpongeModulePlayer::class,
        SpongeModuleServer::class,
        SpongeModuleText::class
))
interface SpongeFastCraftComponent : FastCraftComponent
