package net.benwoodworth.fastcraft.implementations.sponge

import dagger.Component
import javax.inject.Singleton

/**
 * Sponge implementation of [Component].
 */
@Singleton
@Component(modules = [
    SpongeFastCraftModule::class
])
interface SpongeFastCraftComponent : net.benwoodworth.fastcraft.dependencies.FastCraftComponent
