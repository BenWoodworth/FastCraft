package net.benwoodworth.fastcraft.implementations.sponge

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.FastCraftComponent
import javax.inject.Singleton

/**
 * Sponge implementation of [Component].
 */
@Singleton
@Component(modules = [
    SpongeFastCraftModule::class
])
interface SpongeFastCraftComponent : FastCraftComponent
