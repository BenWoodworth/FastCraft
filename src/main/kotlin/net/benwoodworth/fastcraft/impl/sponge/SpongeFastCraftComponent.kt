package net.benwoodworth.fastcraft.impl.sponge

import dagger.Component
import net.benwoodworth.fastcraft.core.FastCraftComponent
import net.benwoodworth.fastcraft.impl.sponge.dependencies.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.impl.sponge.dependencies.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.dependencies.fastcraft.SpongeModuleFastCraft
import net.benwoodworth.fastcraft.impl.sponge.dependencies.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.impl.sponge.dependencies.item.SpongeModuleItem
import net.benwoodworth.fastcraft.impl.sponge.dependencies.permission.SpongeModulePermission
import net.benwoodworth.fastcraft.impl.sponge.dependencies.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.impl.sponge.dependencies.server.SpongeModuleServer
import net.benwoodworth.fastcraft.impl.sponge.dependencies.text.SpongeModuleText
import javax.inject.Singleton

/**
 * Dagger component for FastCraft's Sponge implementation.
 */
@Singleton @Component(modules = arrayOf(
        SpongeModuleConfig::class,
        SpongeModuleEvent::class,
        SpongeModuleFastCraft::class,
        SpongeModuleGui::class,
        SpongeModuleItem::class,
        SpongeModulePermission::class,
        SpongeModulePlayer::class,
        SpongeModuleServer::class,
        SpongeModuleText::class
))
interface SpongeFastCraftComponent : FastCraftComponent
