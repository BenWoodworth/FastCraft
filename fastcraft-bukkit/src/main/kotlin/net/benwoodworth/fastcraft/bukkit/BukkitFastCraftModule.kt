package net.benwoodworth.fastcraft.bukkit

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.api.FastCraftApi
import net.benwoodworth.fastcraft.api.FastCraftPreferences
import net.benwoodworth.fastcraft.bukkit.api.BukkitFastCraftApiImpl
import net.benwoodworth.fastcraft.bukkit.api.BukkitFastCraftPreferences
import net.benwoodworth.fastcraft.bukkit.api.BukkitFastCraftPreferencesImpl
import net.benwoodworth.fastcraft.bukkit.command.*
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfig_1_7
import net.benwoodworth.fastcraft.bukkit.gui.*
import net.benwoodworth.fastcraft.bukkit.player.*
import net.benwoodworth.fastcraft.bukkit.recipe.*
import net.benwoodworth.fastcraft.bukkit.server.*
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.bukkit.world.*
import net.benwoodworth.fastcraft.platform.command.FcCommandRegistry
import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import net.benwoodworth.fastcraft.platform.player.FcSound
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.server.*
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextConverter
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemOrderComparator
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Server
import org.bukkit.inventory.ItemFactory
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler
import java.util.logging.Logger
import javax.inject.Provider

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
    fun providePlugin(): Plugin {
        return plugin
    }

    @Provides
    fun provideBukkitVersion(): BukkitVersion {
        return bukkitVersion
    }

    @Provides
    fun provideServer(plugin: Plugin): Server {
        return plugin.server
    }

    @Provides
    fun provideFastCraftApi(instance: BukkitFastCraftApiImpl): FastCraftApi {
        return instance
    }

    @Provides
    fun provideBukkitFastCraftPreferences(instance: BukkitFastCraftPreferencesImpl): BukkitFastCraftPreferences {
        return instance
    }

    @Provides
    fun provideFastCraftPreferences(instance: BukkitFastCraftPreferences): FastCraftPreferences {
        return instance
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
    fun provideLogger(): Logger {
        return plugin.logger
    }

    @Provides
    fun provideFcLogger(instance: BukkitFcLogger_1_7): FcLogger {
        return instance
    }

    @Provides
    fun provideFcPluginData(instance: BukkitFcPluginData_1_7): FcPluginData {
        return instance
    }

    @Provides
    fun provideFcConfigFactory(instance: BukkitFcConfig_1_7.Factory): FcConfig.Factory {
        return instance
    }

    @Provides
    fun provideFcGuiFactory(instance: BukkitFcGui_1_7.Factory): FcGui.Factory {
        return instance
    }

    @Provides
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
    fun provideFcGuiLayoutFactory(instance: BukkitFcGuiLayout_1_7.Factory): BukkitFcGuiLayout.Factory {
        return instance
    }

    @Provides
    fun provideFcItemStackFactory(
        instance_1_7: Provider<BukkitFcItemStack_1_7.Factory>,
    ): FcItemStack.Factory {
        return when {
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
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
    fun provideFcMaterialComparator(
        instance_1_13: Provider<BukkitFcItemOrderComparator_1_13>,
        instance_1_7: Provider<BukkitFcItemOrderComparator_1_7>,
    ): FcItemOrderComparator {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    fun provideFcPlayerProvider(instance: BukkitFcPlayer_1_7.Provider): FcPlayer.Provider {
        return instance
    }

    @Provides
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
    fun provideCraftingInventoryViewFactory(
        instance_1_14: Provider<CraftingInventoryViewFactory_1_14>,
        instance_1_9: Provider<CraftingInventoryViewFactory_1_9>,
        instance_1_7: Provider<CraftingInventoryViewFactory_1_7>,
    ): CraftingInventoryViewFactory {
        return when {
            bukkitVersion >= VERSION_1_14 -> instance_1_14.get()
            bukkitVersion >= VERSION_1_9 -> instance_1_9.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
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
    fun provideFcTaskFactory(instance: BukkitFcTask_1_7.Factory): FcTask.Factory {
        return instance
    }

    @Provides
    fun provideFcTextFactory(instance: BukkitFcText_1_7.Factory): FcText.Factory {
        return instance
    }

    @Provides
    fun provideFcTextColorFactory(instance: BukkitFcTextColor_1_7.Factory): FcTextColor.Factory {
        return instance
    }

    @Provides
    fun provideFcTextConverter(instance: BukkitFcTextConverter_1_7): FcTextConverter {
        return instance
    }

    @Provides
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
    fun provideCommandAdapterFactory(
        instance: BukkitFcCommandAdapter_1_7.Factory,
    ): BukkitFcCommandAdapter.Factory {
        return instance
    }

    @Provides
    fun provideCommandRegistry(
        instance: BukkitFcCommandRegistry_1_7,
    ): FcCommandRegistry {
        return instance
    }

    @Provides
    fun provideCommandSourceFactory(
        instance: BukkitFcCommandSource_1_7.Factory,
    ): BukkitFcCommandSource.Factory {
        return instance
    }

    @Provides
    fun provideSoundFactory(
        instance_1_9: Provider<BukkitFcSound_1_9.Factory>,
        instance_1_7: Provider<BukkitFcSound_1_7.Factory>,
    ): FcSound.Factory {
        return when {
            bukkitVersion >= VERSION_1_9 -> instance_1_9.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    fun provideFcServer(
        instance: BukkitFcServer_1_7,
    ): FcServer {
        return instance
    }

    @Provides
    fun provideFcPermissionFactory(
        instance: BukkitFcPermission_1_7.Factory,
    ): FcPermission.Factory {
        return instance
    }

    @Provides
    fun provideLegacyMaterialInfo(
        instance_1_7: Provider<LegacyMaterialInfo_1_7>,
        instance_1_13: Provider<LegacyMaterialInfo_1_13>,
    ): LegacyMaterialInfo {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    fun provideFcSoundOperations(
        instance_1_7: Provider<BukkitFcSound_1_7.Operations>,
    ): FcSound.Operations {
        return instance_1_7.get()
    }

    @Provides
    fun provideFcPlayerOperations(
        instance_1_7: Provider<BukkitFcPlayer_1_7.Operations>,
    ): FcPlayer.Operations {
        return instance_1_7.get()
    }

    @Provides
    fun provideFcItemOperations(
        instance_1_7: Provider<BukkitFcItem_1_7.Operations>,
        instance_1_9: Provider<BukkitFcItem_1_9.Operations>,
        instance_1_13: Provider<BukkitFcItem_1_13.Operations>,
        instance_1_15: Provider<BukkitFcItem_1_15.Operations>,
    ): FcItem.Operations {
        return when {
            bukkitVersion >= VERSION_1_15 -> instance_1_15.get()
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_9 -> instance_1_9.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    fun provideFcItemStackOperations(
        instance_1_7: Provider<BukkitFcItemStack_1_7.Operations>,
        instance_1_13: Provider<BukkitFcItemStack_1_13.Operations>,
    ): FcItemStack.Operations {
        return when {
            bukkitVersion >= VERSION_1_13 -> instance_1_13.get()
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }

    @Provides
    fun provideFcTextColorOperations(
        instance_1_7: Provider<BukkitFcTextColor_1_7.Operations>,
    ): FcTextColor.Operations {
        return when {
            bukkitVersion >= VERSION_1_7 -> instance_1_7.get()
            else -> instance_1_7.get()
        }
    }
}
