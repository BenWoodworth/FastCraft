package net.benwoodworth.fastcraft.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.sponge.dependencies.inventory.SpongeItem
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent

/**
 * Created by ben on 6/5/17.
 */
class SpongeFastCraft : FastCraftImplementation<FastCraft<SpongeItem>> {

    lateinit override var instance: FastCraft<SpongeItem>
        private set

    @Listener
    fun onPreInit(event: GamePreInitializationEvent) {
        instance = DaggerSpongeFastCraftComponent.builder()
                .spongeFastCraftModule(SpongeFastCraftModule(this))
                .build()
                .getFastCraft()
    }
}
