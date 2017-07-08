package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler
import javax.inject.Singleton

/**
 * Sponge implementation of [ModuleServer].
 */
@Module
class SpongeModuleServer : ModuleServer {

    @Provides @Singleton
    override fun taskScheduler(): TaskScheduler {
        return SpongeTaskScheduler()
    }
}
