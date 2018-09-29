package net.benwoodworth.fastcraft

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.platform.api.PlatformModule
import javax.inject.Singleton

@Module
class FastCraftPlatformModule(
    private var module: PlatformModule
) : PlatformModule {

    @Provides
    override fun createConfigBuilder() = module.createConfigBuilder()

    @Provides
    override fun createGuiBuilder() = module.createGuiBuilder()

    @Provides
    override fun createItemBuilder() = module.createItemBuilder()

    @Provides
    @Singleton
    override fun getItemTypeFactory() = module.getItemTypeFactory()

    @Provides
    @Singleton
    override fun getPlayerProvider() = module.getPlayerProvider()

    @Provides
    @Singleton
    override fun getRecipeProvider() = module.getRecipeProvider()

    @Provides
    @Singleton
    override fun getPluginProvider() = module.getPluginProvider()

    @Provides
    override fun createTaskBuilder() = module.createTaskBuilder()
}