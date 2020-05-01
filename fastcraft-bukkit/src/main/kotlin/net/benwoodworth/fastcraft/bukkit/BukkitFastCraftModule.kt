package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.bukkit.command.*
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfig_1_7
import net.benwoodworth.fastcraft.bukkit.gui.*
import net.benwoodworth.fastcraft.bukkit.item.*
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerEvents_1_13
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerEvents_1_7
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer_1_7
import net.benwoodworth.fastcraft.bukkit.recipe.*
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger_1_7
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData_1_7
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTask_1_7
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemTypeComparator
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTask
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
    private val plugin: BukkitFastCraft,
) {
    private val bukkitVersion = BukkitVersion.parse(plugin.server.bukkitVersion)

    private companion object {
        val VERSION_1_15 = BukkitVersion.parse("1.15")
        val VERSION_1_14 = BukkitVersion.parse("1.14")
        val VERSION_1_13 = BukkitVersion.parse("1.13")
        val VERSION_1_12 = BukkitVersion.parse("1.12")
        val VERSION_1_9 = BukkitVersion.parse("1.9")
        val VERSION_1_8 = BukkitVersion.parse("1.8")
        val VERSION_1_7 = BukkitVersion.parse("1.7")
    }

    @Provides
    @Singleton
    fun providePlugin(): Plugin {
        return plugin
    }

    @Provides
    @Singleton
    fun provideBukkitVersion(): BukkitVersion {
        return bukkitVersion
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
        return BukkitFcLogger_1_7(plugin.logger)
    }

    @Provides
    @Singleton
    fun provideFcPluginData(instance: BukkitFcPluginData_1_7): FcPluginData {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcConfigFactory(instance: BukkitFcConfig_1_7.Factory): FcConfig.Factory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiFactory(instance: BukkitFcGui_1_7.Factory): FcGui.Factory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcGuiButtonFactory(
        instance_1_13: Provider<BukkitFcGuiButton_1_13.Factory>,
        instance_1_8: Provider<BukkitFcGuiButton_1_8.Factory>,
        instance_1_7: Provider<BukkitFcGuiButton_1_7.Factory>,
    ): BukkitFcGuiButton.Factory {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_8 -> instance_1_8.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcGuiLayoutFactory(instance: BukkitFcGuiLayout_1_7.Factory): BukkitFcGuiLayout.Factory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcItemFactory(
        instance_1_13: Provider<BukkitFcItem_1_13.Factory>,
        instance_1_7: Provider<BukkitFcItem_1_7.Factory>,
    ): FcItem.Factory {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcItemTypes(
        instance_1_15: Provider<BukkitFcItemTypes_1_15>,
        instance_1_13: Provider<BukkitFcItemTypes_1_15>,
        instance_1_9: Provider<BukkitFcItemTypes_1_15>,
        instance_1_7: Provider<BukkitFcItemTypes_1_7>,
    ): FcItemTypes {
        return when {
            bukkitVersion >= VERSION_1_15 -> instance_1_15.get()
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_9 -> instance_1_9.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcItemTypesComparator(
        instance_1_13: Provider<BukkitFcItemTypeComparator_1_13>,
        instance_1_7: Provider<BukkitFcItemTypeComparator_1_7>,
    ): FcItemTypeComparator {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcPlayerProvider(instance: BukkitFcPlayer_1_7.Provider): FcPlayer.Provider {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcCraftingRecipePreparedFactory(
        instance_1_12: Provider<BukkitFcCraftingRecipePrepared_1_12.Factory>,
        instance_1_7: Provider<BukkitFcCraftingRecipePrepared_1_7.Factory>,
    ): BukkitFcCraftingRecipePrepared.Factory {
        return when {
            bukkitVersion >= VERSION_1_12 -> instance_1_12.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcRecipeProvider(
        instance_1_15: Provider<BukkitFcRecipeProvider_1_15>,
        instance_1_7: Provider<BukkitFcRecipeProvider_1_7>,
    ): FcRecipeProvider {
        return when {
            bukkitVersion >= VERSION_1_15 -> instance_1_15.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideCraftingInventoryViewFactory(
        instance_1_14: Provider<CraftingInventoryViewFactory_1_14>,
        instance_1_7: Provider<CraftingInventoryViewFactory_1_7>,
    ): CraftingInventoryViewFactory {
        return when {
            bukkitVersion >= VERSION_1_14 -> instance_1_14.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }


    @Provides
    @Singleton
    fun provideFcCraftingRecipeFactory(
        instance_1_15: Provider<BukkitFcCraftingRecipe_1_15.Factory>,
        instance_1_13: Provider<BukkitFcCraftingRecipe_1_13.Factory>,
        instance_1_12: Provider<BukkitFcCraftingRecipe_1_12.Factory>,
        instance_1_7: Provider<BukkitFcCraftingRecipe_1_7.Factory>,
    ): BukkitFcCraftingRecipe.Factory {
        return when {
            bukkitVersion >= VERSION_1_15 -> instance_1_15.get()
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_12 -> instance_1_12.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcPlayerEvents(
        instance_1_13: Provider<BukkitFcPlayerEvents_1_13>,
        instance_1_7: Provider<BukkitFcPlayerEvents_1_7>,
    ): FcPlayerEvents {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideFcTaskFactory(instance: BukkitFcTask_1_7.Factory): FcTask.Factory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextFactory(instance: BukkitFcTextFactory_1_7): FcTextFactory {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextColors(instance: BukkitFcTextColors_1_7): FcTextColors {
        return instance
    }

    @Provides
    @Singleton
    fun provideFcTextConverter(instance: BukkitFcTextConverter_1_7): FcTextConverter {
        return instance
    }

    @Provides
    @Singleton
    fun provideBukkitLocalizer(
        instance_1_13: Provider<BukkitLocalizer_1_13>,
        instance_1_7: Provider<BukkitLocalizer_1_7>,
    ): BukkitLocalizer {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    @Singleton
    fun provideCommandAdapterFactory(
        instance: BukkitFcCommandAdapter_1_7.Factory,
    ): BukkitFcCommandAdapter.Factory {
        return instance
    }

    @Provides
    @Singleton
    fun provideCommandRegistry(
        instance: BukkitFcCommandRegistry_1_7,
    ): FcCommandRegistry {
        return instance
    }

    @Provides
    @Singleton
    fun provideCommandSourceFactory(
        instance: BukkitFcCommandSource_1_7.Factory,
    ): BukkitFcCommandSource.Factory {
        return instance
    }
}
