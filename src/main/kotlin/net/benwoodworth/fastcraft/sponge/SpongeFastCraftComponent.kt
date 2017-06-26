package net.benwoodworth.fastcraft.sponge

import dagger.Component
import net.benwoodworth.fastcraft.core.FastCraftComponent
import javax.inject.Singleton

/**
 * Dagger component for Sponge FastCraft implementation.
 */
@Singleton @Component(modules = arrayOf(
        //SpongeFastCraftModule::class
))
interface SpongeFastCraftComponent : FastCraftComponent
