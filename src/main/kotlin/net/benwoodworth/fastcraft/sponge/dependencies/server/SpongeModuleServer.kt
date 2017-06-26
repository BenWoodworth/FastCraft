package net.benwoodworth.fastcraft.sponge.dependencies.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.server.Logger
import net.benwoodworth.fastcraft.core.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler

/**
 * Sponge implementation of [ModuleServer].
 */
@Module
class SpongeModuleServer : ModuleServer {

    @Provides
    override fun logger(): Logger {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    override fun taskScheduler(): TaskScheduler {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
