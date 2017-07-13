package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.core.dependencies.server.TaskBuilder
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import org.spongepowered.api.scheduler.Task as Sponge_Task

/**
 * Sponge implementation of [ModuleServer].
 */
@Module
class SpongeModuleServer(
        private val fastCraft: SpongeFastCraft
) : ModuleServer {

    @Provides
    override fun taskBuilder(): TaskBuilder {
        return SpongeTaskBuilder(Sponge_Task.builder(), fastCraft)
    }
}
