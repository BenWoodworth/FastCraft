package net.benwoodworth.fastcraft.impl.sponge.dependencies.config

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigManager
import net.benwoodworth.fastcraft.core.dependencies.config.ModuleConfig
import javax.inject.Singleton

/**
 * Sponge implementation of [ModuleConfig].
 */
@Module
class SpongeModuleConfig : ModuleConfig {

    @Provides @Singleton
    override fun configService(): ConfigManager {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
