package net.benwoodworth.fastcraft

import dagger.Component
import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.platform.FcPlatform
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

class FastCraftFactory(
    private val dependencies: FcPlatform
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
    internal inner class DaggerModule : FcPlatform {

        @Provides
        override fun getLogger(): FcLogger {
            return dependencies.getLogger()
        }

        @Provides
        override fun getPluginData(): FcPluginData {
            return dependencies.getPluginData()
        }

        @Provides
        override fun getConfigFactory(): FcConfigFactory {
            return dependencies.getConfigFactory()
        }

        @Provides
        override fun getGuiFactory(): FcGuiFactory {
            return dependencies.getGuiFactory()
        }

        @Provides
        override fun getItemFactory(): FcItemFactory {
            return dependencies.getItemFactory()
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
        override fun getTaskFactory(): FcTaskFactory {
            return dependencies.getTaskFactory()
        }

        @Provides
        override fun getTextFactory(): FcTextFactory {
            return dependencies.getTextFactory()
        }
    }
}
