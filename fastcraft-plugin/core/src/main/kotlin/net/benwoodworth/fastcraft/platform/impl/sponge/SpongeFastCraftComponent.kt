package net.benwoodworth.fastcraft.platform.impl.sponge

import dagger.Component
import net.benwoodworth.fastcraft.platform.FastCraftComponent
import javax.inject.Singleton

/**
 * Sponge implementation of [Component].
 */
@Singleton
@Component(modules = [
    SpongeFastCraftModule::class
])
interface SpongeFastCraftComponent : FastCraftComponent
