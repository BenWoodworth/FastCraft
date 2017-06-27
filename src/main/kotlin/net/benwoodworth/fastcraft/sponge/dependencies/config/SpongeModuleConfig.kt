package net.benwoodworth.fastcraft.sponge.dependencies.config

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigFactory
import net.benwoodworth.fastcraft.core.dependencies.config.ModuleConfig
import javax.inject.Singleton

/**
 * Sponge implementation of [ModuleConfig].
 */
@Module
class SpongeModuleConfig : ModuleConfig {

    @Provides @Singleton
    override fun configFactory(): ConfigFactory {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
