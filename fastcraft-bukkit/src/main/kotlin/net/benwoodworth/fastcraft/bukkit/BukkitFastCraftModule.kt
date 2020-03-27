package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory_1_7_5_R01
import net.benwoodworth.fastcraft.bukkit.gui.*
import net.benwoodworth.fastcraft.bukkit.item.*
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerEvents_1_13_R01
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerEvents_1_7_5_R01
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider_1_7_5_R01
import net.benwoodworth.fastcraft.bukkit.recipe.*
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger_1_7_5_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData_1_7_5_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskFactory_1_7_5_R01
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
class BukkitFastCraftModule(
    private val plugin: BukkitFastCraft
) {
    private val bukkitVersion = BukkitVersion.parse(plugin.server.bukkitVersion)

    private companion object {
        val VERSION_1_15_R01 = BukkitVersion.parse("1.15-R0.1")
        val VERSION_1_14_R01 = BukkitVersion.parse("1.14-R0.1")
        val VERSION_1_13_R01 = BukkitVersion.parse("1.13-R0.1")
        val VERSION_1_13_1_R01 = BukkitVersion.parse("1.13.1-R0.1")
        val VERSION_1_8_R01 = BukkitVersion.parse("1.8-R0.1")
        val VERSION_1_7_5_R01 = BukkitVersion.parse("1.7.5-R0.1")
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
        return BukkitFcLogger_1_7_5_R01(plugin.logger)
    }

    @Provides
    @Singleton
    fun provideFcPluginData(instance: BukkitFcPluginData_1_7_5_R01): FcPluginData {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcConfigFactory(instance: BukkitFcConfigFactory_1_7_5_R01): FcConfigFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiFactory(instance: BukkitFcGuiFactory_1_7_5_R01): FcGuiFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiButtonFactory(
        instance_1_13: Provider<BukkitFcGuiButton_1_13_R01.Factory>,
        instance_1_8: Provider<BukkitFcGuiButton_1_8_R01.Factory>,
        instance_1_7_5: Provider<BukkitFcGuiButton_1_7_5_R01.Factory>
    ): BukkitFcGuiButton.Factory {
        return when {
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_8_R01 -> instance_1_8.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }

    @Provides
    @Singleton
    fun provideBukkitFcItemFactory(instance: BukkitFcItemFactory_1_7_5_R01): BukkitFcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemTypes(
        instance_1_13: Provider<BukkitFcItemTypes_1_13_R01>,
        instance_1_7_5: Provider<BukkitFcItemTypes_1_7_5_R01>
    ): FcItemTypes {
        return when {
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }

    @Provides
    @Singleton
    fun provideItemStackNameProvider(instance: ItemStackNameProvider_1_7_5_R01): ItemStackNameProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcPlayerProvider(instance: BukkitFcPlayerProvider_1_7_5_R01): FcPlayerProvider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcRecipeService(
        instance_1_13: Provider<BukkitFcRecipeService_1_13_R01>,
        instance_1_7_5: Provider<BukkitFcRecipeService_1_7_5_R01>
    ): FcRecipeService {
        return when {
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }

    @Provides
    @Singleton
    fun provideCraftingInventoryViewFactory(
        instance_1_14: Provider<CraftingInventoryViewFactory_1_14_R01>,
        instance_1_7_5: Provider<CraftingInventoryViewFactory_1_7_5_R01>
    ): CraftingInventoryViewFactory {
        return when {
            bukkitVersion >= VERSION_1_14_R01 -> instance_1_14.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }


    @Provides
    @Singleton
    fun provideFcCraftingRecipeFactory(
        instance_1_13_1: Provider<BukkitFcCraftingRecipe_1_13_1_R01.Factory>,
        instance_1_13: Provider<BukkitFcCraftingRecipe_1_13_R01.Factory>,
        instance_1_7_5: Provider<BukkitFcCraftingRecipe_1_7_5_R01.Factory>
    ): BukkitFcCraftingRecipe.Factory {
        return when {
            bukkitVersion >= VERSION_1_13_1_R01 -> instance_1_13_1.get()
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcPlayerEvents(
        instance_1_13: Provider<BukkitFcPlayerEvents_1_13_R01>,
        instance_1_7_5: Provider<BukkitFcPlayerEvents_1_7_5_R01>
    ): FcPlayerEvents {
        return when {
            bukkitVersion >= VERSION_1_13_R01 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7_5_R01 -> instance_1_7_5.get()
            else -> instance_1_7_5.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcTaskFactory(instance: BukkitFcTaskFactory_1_7_5_R01): FcTaskFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextFactory(instance: BukkitFcTextFactory_1_7_5_R01): FcTextFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextColors(instance: BukkitFcTextColors_1_7_5_R01): FcTextColors {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextConverter(instance: BukkitFcTextConverter_1_7_5_R01): FcTextConverter {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitLocalizer(instance: BukkitLocalizer_1_7_5_R01): BukkitLocalizer {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemFactory(instance: BukkitFcItemFactory_1_7_5_R01): FcItemFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideIngredientProductProvider(instance: IngredientRemnantProvider_1_7_5_R01): IngredientRemnantProvider {
        return instance
    }
}
