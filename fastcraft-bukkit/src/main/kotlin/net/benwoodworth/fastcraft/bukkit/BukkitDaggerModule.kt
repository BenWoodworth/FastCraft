package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory_1_15_R01
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiFactory_1_15_R01
import net.benwoodworth.fastcraft.bukkit.item.*
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerEvents_1_15_R01
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider_1_15_R01
import net.benwoodworth.fastcraft.bukkit.recipe.*
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger_1_15_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData_1_15_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskFactory_1_15_R01
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Server
import org.bukkit.inventory.ItemFactory
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("unused")
@Module
class BukkitDaggerModule(
    private val plugin: BukkitFastCraftPlugin
) {
    private val bukkitVersion = BukkitVersion.parse(plugin.server.bukkitVersion)

    private companion object {
        val VERSION_1_15_R01 = BukkitVersion.parse("1.15-R0.1")
        val VERSION_1_13_R01 = BukkitVersion.parse("1.13-R0.1")
    }

    @Provides
    @Singleton
    fun providePlugin(): Plugin {
        return plugin
    }

    @Provides
    fun provideServer(plugin: Plugin): Server {
        return plugin.server
    }

    @Provides
    fun provideItemFactory(server: Server): ItemFactory {
        return server.itemFactory
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
        return BukkitFcLogger_1_15_R01(plugin.logger)
    }

    @Provides
    @Singleton
    fun provideFcPluginData(instance: BukkitFcPluginData_1_15_R01): FcPluginData {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcConfigFactory(instance: BukkitFcConfigFactory_1_15_R01): FcConfigFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiFactory(instance: BukkitFcGuiFactory_1_15_R01): FcGuiFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitFcItemFactory(instance: BukkitFcItemFactory_1_15_R01): BukkitFcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemTypes(instance: BukkitFcItemTypes_1_15_R01): FcItemTypes {
        return instance
    }

    @Provides
    @Singleton
    fun provideItemStackNameProvider(instance: ItemStackNameProvider_1_15_R01): ItemStackNameProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcPlayerProvider(instance: BukkitFcPlayerProvider_1_15_R01): FcPlayerProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcRecipeService(instance: BukkitFcRecipeService_1_15_R01): FcRecipeService {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcCraftingRecipeFactory(
        instance_1_15: Provider<BukkitFcCraftingRecipe_1_15_R01.Factory>,
        instance_1_13: Provider<BukkitFcCraftingRecipe_1_13_R01.Factory>
    ): BukkitFcCraftingRecipe.Factory {
        return when {
            bukkitVersion >= VERSION_1_15_R01 -> instance_1_15.get()
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            else -> instance_1_13.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcPlayerEvents(instance: BukkitFcPlayerEvents_1_15_R01): FcPlayerEvents {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTaskFactory(instance: BukkitFcTaskFactory_1_15_R01): FcTaskFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextFactory(instance: BukkitFcTextFactory_1_15_R01): FcTextFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextColors(instance: BukkitFcTextColors_1_15_R01): FcTextColors {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextConverter(instance: BukkitFcTextConverter_1_15_R01): FcTextConverter {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitLocalizer(instance: BukkitLocalizer_1_15_R01): BukkitLocalizer {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemFactory(instance: BukkitFcItemFactory_1_15_R01): FcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideIngredientProductProvider(instance: IngredientRemnantProvider_1_15_R01): IngredientRemnantProvider {
        return instance
    }
}
