package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcRecipeService_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcServerListeners_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcServerListeners
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.plugin.Plugin
import javax.inject.Singleton

@Module
class BukkitDaggerModule_1_13_00_R01(
    private val plugin: Plugin
) {

    @Provides
    @Singleton
    fun providePlugin(): Plugin {
        return plugin
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
    fun provideFcItemFactory(instance: BukkitFcItemFactory_1_13_00_R01): FcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemTypes(instance: BukkitFcItemTypes_1_13_00_R01): FcItemTypes {
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
    fun provideFcTaskFactory(instance: BukkitFcTaskFactory_1_13_00_R01): FcTaskFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitFcRawTextFactory(instance: BukkitFcRawTextFactory_1_13_00_R01): BukkitFcRawTextFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcLegacyTextFactory(instance: BukkitFcLegacyTextFactory_1_13_00_R01): FcLegacyTextFactory {
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
    fun provideFcServerListeners(instance: BukkitFcServerListeners_1_13_00_R01): FcServerListeners {
        return instance
    }
}
