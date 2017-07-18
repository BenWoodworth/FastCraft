package net.benwoodworth.fastcraft.impl.sponge.server

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.server.ModuleServer
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import javax.inject.Singleton
import org.spongepowered.api.scheduler.Task as Sponge_Task

/**
 * Sponge implementation of [ModuleServer].
 */
@Module
class SpongeModuleServer(
        private val fastCraft: SpongeFastCraft
) : ModuleServer {

    @Provides
    override fun taskBuilder(): Task.Builder {
        return SpongeTask.Builder(Sponge_Task.builder(), fastCraft)
    }

    @Provides @Singleton
    override fun pluginProvider(): Plugin.Provider {
        return SpongePlugin.Provider()
    }
}
