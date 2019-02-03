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
import net.benwoodworth.fastcraft.bukkit.text.*
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
import net.benwoodworth.fastcraft.platform.text.*

// config
inline val FcConfig.bukkit get() = this as BukkitFcConfig
inline val FcConfigEntry.bukkit get() = this as BukkitFcConfigEntry
inline val FcConfigFactory.bukkit get() = this as BukkitFcConfigFactory

// event
inline val FcEvent.bukkit get() = this as BukkitFcEvent
//inline val FcEventListener.bukkit get() = this as BukkitFcEventListener

// gui
inline val <T : FcGuiLayout> FcGui<T>.bukkit get() = this as BukkitFcGui<T>
inline val FcGuiClickEvent.bukkit get() = this as BukkitFcGuiClickEvent
inline val FcGuiCloseEvent.bukkit get() = this as BukkitFcGuiCloseEvent
inline val FcGuiFactory.bukkit get() = this as BukkitFcGuiFactory
inline val FcGuiLayout.bukkit get() = this as BukkitFcGuiLayout
inline val FcGuiLayoutGrid.bukkit get() = this as BukkitFcGuiLayoutGrid
inline val FcGuiButton.bukkit get() = this as BukkitFcGuiButton

// item
inline val FcItem.bukkit get() = this as BukkitFcItem
inline val FcItemFactory.bukkit get() = this as BukkitFcItemFactory
inline val FcItemType.bukkit get() = this as BukkitFcItemType
inline val FcItemTypes.bukkit get() = this as BukkitFcItemTypes

// locale
inline val FcLocale.bukkit get() = this as BukkitFcLocale
inline val FcTranslatable.bukkit get() = this as BukkitFcTranslatable

// player
inline val FcPlayer.bukkit get() = this as BukkitFcPlayer
inline val FcPlayerProvider.bukkit get() = this as BukkitFcPlayerProvider

// recipe
inline val FcCraftingRecipe.bukkit get() = this as BukkitFcCraftingRecipe
inline val FcIngredient.bukkit get() = this as BukkitFcIngredient
inline val FcRecipeService.bukkit get() = this as BukkitFcRecipeService

// server
inline val FcLogger.bukkit get() = this as BukkitFcLogger
inline val FcPluginData.bukkit get() = this as BukkitFcPluginData
inline val FcTask.bukkit get() = this as BukkitFcTask
inline val FcTaskFactory.bukkit get() = this as BukkitFcTaskFactory

// text
inline val FcLegacyText.bukkit get() = this as BukkitFcLegacyText
inline val FcLegacyTextFactory.bukkit get() = this as BukkitFcLegacyTextFactory
inline val FcText.bukkit get() = this as BukkitFcText
inline val FcTextColor.bukkit get() = this as BukkitFcTextColor
inline val FcTextColors.bukkit get() = this as BukkitFcTextColors
inline val FcTextFactory.bukkit get() = this as BukkitFcTextFactory
