package net.benwoodworth.fastcraft.platform.api

import net.benwoodworth.fastcraft.platform.api.config.FcConfigBuilder
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemTypeFactory
import net.benwoodworth.fastcraft.platform.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.api.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.api.text.FcTextBuilder

interface PlatformModule {

    fun createConfigBuilder(): FcConfigBuilder

    fun createGuiBuilder(): FcGuiBuilder

    fun createItemBuilder(): FcItemBuilder

    fun getItemTypeFactory(): FcItemTypeFactory

    fun getPlayerProvider(): FcPlayerProvider

    fun getRecipeProvider(): FcRecipeProvider

    fun getPluginProvider(): FcPlayerProvider

    fun createTaskBuilder(): FcTextBuilder
}