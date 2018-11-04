package net.benwoodworth.fastcraft

import dagger.Component
import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.platform.FcPlatformDependencies
import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import net.benwoodworth.fastcraft.platform.gui.FcGuiBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder

class FastCraftFactory(
    private val dependencies: FcPlatformDependencies
) {

    private val component = DaggerFastCraftFactory_DaggerComponent.builder()
        .daggerModule(DaggerModule())
        .build()

    fun create(): FastCraft {
        return component.getFastCraft()
    }

    @Component(modules = [DaggerModule::class])
    internal interface DaggerComponent {

        fun getFastCraft(): FastCraft
    }

    @Module
    internal inner class DaggerModule : FcPlatformDependencies {

        @Provides
        override fun getLogger(): FcLogger {
            return dependencies.getLogger()
        }

        @Provides
        override fun getPluginData(): FcPluginData {
            return dependencies.getPluginData()
        }

        @Provides
        override fun createConfigBuilder(): FcConfigBuilder {
            return dependencies.createConfigBuilder()
        }

        @Provides
        override fun createGuiBuilder(): FcGuiBuilder {
            return dependencies.createGuiBuilder()
        }

        @Provides
        override fun createItemBuilder(): FcItemBuilder {
            return dependencies.createItemBuilder()
        }

        @Provides
        override fun getItemTypes(): FcItemTypes {
            return dependencies.getItemTypes()
        }

        @Provides
        override fun getPlayerProvider(): FcPlayerProvider {
            return dependencies.getPlayerProvider()
        }

        @Provides
        override fun getRecipeService(): FcRecipeService {
            return dependencies.getRecipeService()
        }

        @Provides
        override fun createTaskBuilder(): FcTaskBuilder {
            return dependencies.createTaskBuilder()
        }

        @Provides
        override fun createTextBuilder(): FcTextBuilder {
            return dependencies.createTextBuilder()
        }
    }
}
