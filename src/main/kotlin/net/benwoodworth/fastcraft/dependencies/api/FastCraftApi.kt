package net.benwoodworth.fastcraft.dependencies.api

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.FcText

/**
 * A FastCraft API for use by other plugins.
 */
interface FastCraftApi {

    /**
     * Get an instance of a [FcText.Builder].
     */
    fun getTextBuilder(): FcText.Builder

    /**
     * Get an instance of a [GuiFactory].
     */
    fun getGuiFactory(): GuiFactory

    /**
     * Get an instance of a [FcItemBuilder].
     */
    fun getItemBuilder(): FcItemBuilder

    /**
     * Get an instance of a [FcPlayerProvider].
     */
    fun getPlayerProvider(): FcPlayerProvider
}
