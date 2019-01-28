
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
val FcConfig.bukkit get() = this as BukkitFcConfig
val FcConfigEntry.bukkit get() = this as BukkitFcConfigEntry
val FcConfigFactory.bukkit get() = this as BukkitFcConfigFactory

// event
val FcEvent.bukkit get() = this as BukkitFcEvent
//val FcEventListener.bukkit get() = this as BukkitFcEventListener

// gui
val <T : FcGuiLayout> FcGui<T>.bukkit get() = this as BukkitFcGui<T>
val FcGuiClickEvent.bukkit get() = this as BukkitFcGuiClickEvent
val FcGuiCloseEvent.bukkit get() = this as BukkitFcGuiCloseEvent
val FcGuiFactory.bukkit get() = this as BukkitFcGuiFactory
val FcGuiLayout.bukkit get() = this as BukkitFcGuiLayout
val FcGuiLayoutGrid.bukkit get() = this as BukkitFcGuiLayoutGrid
val FcGuiLayoutSlot.bukkit get() = this as BukkitFcGuiLayoutSlot

// item
val FcItem.bukkit get() = this as BukkitFcItem
val FcItemFactory.bukkit get() = this as BukkitFcItemFactory
val FcItemType.bukkit get() = this as BukkitFcItemType
val FcItemTypes.bukkit get() = this as BukkitFcItemTypes

// locale
val FcLocale.bukkit get() = this as BukkitFcLocale
val FcTranslatable.bukkit get() = this as BukkitFcTranslatable

// player
val FcPlayer.bukkit get() = this as BukkitFcPlayer
val FcPlayerProvider.bukkit get() = this as BukkitFcPlayerProvider

// recipe
val FcCraftingRecipe.bukkit get() = this as BukkitFcCraftingRecipe
val FcIngredient.bukkit get() = this as BukkitFcIngredient
val FcRecipeService.bukkit get() = this as BukkitFcRecipeService

// server
val FcLogger.bukkit get() = this as BukkitFcLogger
val FcPluginData.bukkit get() = this as BukkitFcPluginData
val FcTask.bukkit get() = this as BukkitFcTask
val FcTaskFactory.bukkit get() = this as BukkitFcTaskFactory

// text
val FcLegacyText.bukkit get() = this as BukkitFcLegacyText
val FcLegacyTextFactory.bukkit get() = this as BukkitFcLegacyTextFactory
val FcText.bukkit get() = this as BukkitFcText
val FcTextColor.bukkit get() = this as BukkitFcTextColor
val FcTextColors.bukkit get() = this as BukkitFcTextColors
val FcTextFactory.bukkit get() = this as BukkitFcTextFactory
