package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.item.*
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcRecipeService_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.*
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.*
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler
import javax.inject.Singleton

@Module
class BukkitDaggerModule_1_13_00_R01(
    private val plugin: Plugin
) {

    @Suppress("UNUSED_PARAMETER")
    @Provides
    fun eagerLoad(
        fcLogger: FcLogger,
        fcPluginData: FcPluginData,
        fcConfigFactory: FcConfigFactory,
        fcGuiFactory: FcGuiFactory,
        bukkitFcItemFactory: BukkitFcItemFactory,
        fcItemTypes: FcItemTypes,
        itemStackNameProvider: ItemStackNameProvider,
        fcPlayerProvider: FcPlayerProvider,
        fcRecipeService: FcRecipeService,
        fcServer: FcServer,
        fcTaskFactory: FcTaskFactory,
        fcTextFactory: FcTextFactory,
        fcTextColors: FcTextColors,
        bukkitFcTextConverter: BukkitFcTextConverter,
        bukkitLocalizer: BukkitLocalizer
    ): BukkitFastCraftFactory.EagerLoad {
        return BukkitFastCraftFactory.EagerLoad
    }

    @Provides
    @Singleton
    fun providePlugin(): Plugin {
        return plugin
    }

    @Provides
    fun provideServer(): Server {
        return plugin.server
    }

    @Provides
    fun providePluginManager(server: Server): PluginManager {
        return server.pluginManager
    }

    @Provides
    fun provideBukkitScheduler(server: Server): BukkitScheduler {
        return server.scheduler
    }

    @Provides
    @Singleton
    fun provideFcLogger(): FcLogger {
        return BukkitFcLogger_1_13_00_R01(plugin.logger)
    }

    @Provides
    @Singleton
    fun provideFcPluginData(instance: BukkitFcPluginData_1_13_00_R01): FcPluginData {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcConfigFactory(instance: BukkitFcConfigFactory_1_13_00_R01): FcConfigFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiFactory(instance: BukkitFcGuiFactory_1_13_00_R01): FcGuiFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitFcItemFactory(instance: BukkitFcItemFactory_1_13_00_R01): BukkitFcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemTypes(instance: BukkitFcItemTypes_1_13_00_R01): FcItemTypes {
        return instance
    }

    @Provides
    @Singleton
    fun provideItemStackNameProvider(instance: ItemStackNameProvider_1_13_00_R01): ItemStackNameProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcPlayerProvider(instance: BukkitFcPlayerProvider_1_13_00_R01): FcPlayerProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcRecipeService(instance: BukkitFcRecipeService_1_13_00_R01): FcRecipeService {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcServer(instance: BukkitFcServer_1_13_00_R01): FcServer {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTaskFactory(instance: BukkitFcTaskFactory_1_13_00_R01): FcTaskFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextFactory(instance: BukkitFcTextFactory_1_13_00_R01): FcTextFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextColors(instance: BukkitFcTextColors_1_13_00_R01): FcTextColors {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitFcTextConverter(instance: BukkitFcTextConverter_1_13_00_R01): BukkitFcTextConverter {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitLocalizer(instance: BukkitLocalizer_1_13_00_R01): BukkitLocalizer {
        return instance
    }
}
