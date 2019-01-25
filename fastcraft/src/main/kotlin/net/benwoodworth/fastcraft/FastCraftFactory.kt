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

        override val logger: FcLogger
            @Provides get() = dependencies.logger

        override val pluginData: FcPluginData
            @Provides get() = dependencies.pluginData

        override val configFactory: FcConfigFactory
            @Provides get() = dependencies.configFactory

        override val guiFactory: FcGuiFactory
            @Provides get() = dependencies.guiFactory

        override val itemFactory: FcItemFactory
            @Provides get() = dependencies.itemFactory

        override val itemTypes: FcItemTypes
            @Provides get() = dependencies.itemTypes

        override val playerProvider: FcPlayerProvider
            @Provides get() = dependencies.playerProvider

        override val recipeService: FcRecipeService
            @Provides get() = dependencies.recipeService

        override val taskFactory: FcTaskFactory
            @Provides get() = dependencies.taskFactory

        override val textFactory: FcTextFactory
            @Provides get() = dependencies.textFactory
    }
}
