package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfig
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigEntry
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory
import net.benwoodworth.fastcraft.bukkit.event.BukkitFcEvent
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGui
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiBuilder
import net.benwoodworth.fastcraft.bukkit.gui.types.BukkitFcGuiChest
import net.benwoodworth.fastcraft.bukkit.gui.types.BukkitFcGuiDispenser
import net.benwoodworth.fastcraft.bukkit.gui.types.BukkitFcGuiHopper
import net.benwoodworth.fastcraft.bukkit.item.*
import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcLocale
import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcTranslatable
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcCraftingRecipe
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcIngredient
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcRecipeService
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTask
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskBuilder
import net.benwoodworth.fastcraft.bukkit.text.*
import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiBuilder
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiChest
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiDispenser
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiHopper
import net.benwoodworth.fastcraft.platform.item.*
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.text.*

inline val FcTask.bukkit: BukkitFcTask
    get() = this as BukkitFcTask

inline val FcTaskBuilder.bukkit: BukkitFcTaskBuilder
    get() = this as BukkitFcTaskBuilder

inline val FcTextFactory.bukkit: BukkitFcTextFactory
    get() = this as BukkitFcTextFactory

inline val FcTextColors.bukkit: BukkitFcTextColors
    get() = this as BukkitFcTextColors

inline val FcTextColor.bukkit: BukkitFcTextColor
    get() = this as BukkitFcTextColor

inline val FcTextBuilder.bukkit: BukkitFcTextBuilder
    get() = this as BukkitFcTextBuilder

inline val FcText.bukkit: BukkitFcText
    get() = this as BukkitFcText

//inline val FcServer.bukkit: BukkitFcServer
//    get() = this as BukkitFcServer

inline val FcPluginData.bukkit: BukkitFcPluginData
    get() = this as BukkitFcPluginData

inline val FcLogger.bukkit: BukkitFcLogger
    get() = this as BukkitFcLogger

inline val FcRecipeService.bukkit: BukkitFcRecipeService
    get() = this as BukkitFcRecipeService

inline val FcIngredient.bukkit: BukkitFcIngredient
    get() = this as BukkitFcIngredient

inline val FcCraftingRecipe.bukkit: BukkitFcCraftingRecipe
    get() = this as BukkitFcCraftingRecipe

inline val FcPlayerProvider.bukkit: BukkitFcPlayerProvider
    get() = this as BukkitFcPlayerProvider

//inline val FcPlayerJoinEvent.bukkit: BukkitFcPlayerJoinEvent
//    get() = this as BukkitFcPlayerJoinEvent

inline val FcPlayer.bukkit: BukkitFcPlayer
    get() = this as BukkitFcPlayer

inline val FcTranslatable.bukkit: BukkitFcTranslatable
    get() = this as BukkitFcTranslatable

inline val FcLocale.bukkit: BukkitFcLocale
    get() = this as BukkitFcLocale

inline val FcItemTypes.bukkit: BukkitFcItemTypes
    get() = this as BukkitFcItemTypes

inline val FcItemType.bukkit: BukkitFcItemType
    get() = this as BukkitFcItemType

inline val FcItemFactory.bukkit: BukkitFcItemFactory
    get() = this as BukkitFcItemFactory

inline val FcItemBuilder.bukkit: BukkitFcItemBuilder
    get() = this as BukkitFcItemBuilder

inline val FcItem.bukkit: BukkitFcItem
    get() = this as BukkitFcItem

//inline val FcGuiCloseEvent.bukkit: BukkitFcGuiCloseEvent
//    get() = this as BukkitFcGuiCloseEvent

//inline val FcGuiClickEvent.bukkit: BukkitFcGuiClickEvent
//    get() = this as BukkitFcGuiClickEvent

inline val FcGuiBuilder.bukkit: BukkitFcGuiBuilder
    get() = this as BukkitFcGuiBuilder

inline val FcGui.bukkit: BukkitFcGui
    get() = this as BukkitFcGui

inline val FcGuiHopper.bukkit: BukkitFcGuiHopper
    get() = this as BukkitFcGuiHopper

//inline val FcGuiGrid.bukkit: BukkitFcGuiGrid
//    get() = this as BukkitFcGuiGrid

inline val FcGuiDispenser.bukkit: BukkitFcGuiDispenser
    get() = this as BukkitFcGuiDispenser

inline val FcGuiChest.bukkit: BukkitFcGuiChest
    get() = this as BukkitFcGuiChest

//inline val FcEventListener.bukkit: BukkitFcEventListener
//    get() = this as BukkitFcEventListener

//inline val FcEventHandler.bukkit: BukkitFcEventHandler
//    get() = this as BukkitFcEventHandler

inline val FcEvent.bukkit: BukkitFcEvent
    get() = this as BukkitFcEvent

inline val FcConfigFactory.bukkit: BukkitFcConfigFactory
    get() = this as BukkitFcConfigFactory

inline val FcConfigEntry.bukkit: BukkitFcConfigEntry
    get() = this as BukkitFcConfigEntry

inline val FcConfig.bukkit: BukkitFcConfig
    get() = this as BukkitFcConfig

//inline val FcPlatformDependencies.bukkit: BukkitFcPlatformDependencies
//    get() = this as BukkitFcPlatformDependencies
