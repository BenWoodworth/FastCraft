package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfig
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigEntry
import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory
import net.benwoodworth.fastcraft.bukkit.event.BukkitFcEvent
import net.benwoodworth.fastcraft.bukkit.gui.*
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemFactory
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemType
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes
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
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskFactory
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColor
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextColors
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextFactory
import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.gui.*
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
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
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextColor
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

inline val FcTask.bukkit: BukkitFcTask
    get() = this as BukkitFcTask

inline val FcTaskFactory.bukkit: BukkitFcTaskFactory
    get() = this as BukkitFcTaskFactory

inline val FcTextFactory.bukkit: BukkitFcTextFactory
    get() = this as BukkitFcTextFactory

inline val FcTextColors.bukkit: BukkitFcTextColors
    get() = this as BukkitFcTextColors

inline val FcTextColor.bukkit: BukkitFcTextColor
    get() = this as BukkitFcTextColor

inline val FcText.bukkit: BukkitFcText
    get() = this as BukkitFcText

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

inline val FcItem.bukkit: BukkitFcItem
    get() = this as BukkitFcItem

inline val <T : FcGuiLayout> FcGui<T>.bukkit: BukkitFcGui<T>
    get() = this as BukkitFcGui<T>

inline val FcGuiClickEvent.bukkit: BukkitFcGuiClickEvent
    get() = this as BukkitFcGuiClickEvent

inline val FcGuiCloseEvent.bukkit: BukkitFcGuiCloseEvent
    get() = this as BukkitFcGuiCloseEvent

inline val FcGuiFactory.bukkit: BukkitFcGuiFactory
    get() = this as BukkitFcGuiFactory

inline val FcGuiLayout.bukkit: BukkitFcGuiLayout
    get() = this as BukkitFcGuiLayout

inline val FcGuiLayoutGrid.bukkit: BukkitFcGuiLayoutGrid
    get() = this as BukkitFcGuiLayoutGrid

inline val FcGuiLayoutSlot.bukkit: BukkitFcGuiLayoutSlot
    get() = this as BukkitFcGuiLayoutSlot

inline val FcEvent.bukkit: BukkitFcEvent
    get() = this as BukkitFcEvent

inline val FcConfigFactory.bukkit: BukkitFcConfigFactory
    get() = this as BukkitFcConfigFactory

inline val FcConfigEntry.bukkit: BukkitFcConfigEntry
    get() = this as BukkitFcConfigEntry

inline val FcConfig.bukkit: BukkitFcConfig
    get() = this as BukkitFcConfig
