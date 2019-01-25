package net.benwoodworth.fastcraft.platform

import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

interface FcPlatformDependencies {

    fun getLogger(): FcLogger

    fun getPluginData(): FcPluginData

    fun createConfigFactory(): FcConfigFactory

    fun createGuiBuilder(): FcGuiFactory

    fun createItemFactory(): FcItemFactory

    fun getItemTypes(): FcItemTypes

    fun getPlayerProvider(): FcPlayerProvider

    fun getRecipeService(): FcRecipeService

    fun createTaskFactory(): FcTaskFactory

    fun createTextFactory(): FcTextFactory
}
