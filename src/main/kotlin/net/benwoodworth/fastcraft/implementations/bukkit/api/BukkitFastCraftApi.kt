package net.benwoodworth.fastcraft.implementations.bukkit.api

import net.benwoodworth.fastcraft.dependencies.api.FastCraftApi
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.api.gui.BukkitGuiFactory
import net.benwoodworth.fastcraft.implementations.bukkit.api.item.BukkitFcItemBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcTextBuilder
import javax.inject.Inject
import javax.inject.Provider

class BukkitFastCraftApi @Inject constructor(
        private val textBuilder: Provider<BukkitFcTextBuilder>,
        private val guiFactory: Provider<BukkitGuiFactory>,
        private val itemBuilder: Provider<BukkitFcItemBuilder>,
        private val playerProvider: Provider<BukkitFcPlayerProvider>
) : FastCraftApi {

    override fun getTextBuilder(): FcTextBuilder {
        return textBuilder.get()
    }

    override fun getGuiFactory(): GuiFactory {
        return guiFactory.get()
    }

    override fun getItemBuilder(): FcItemBuilder {
        return itemBuilder.get()
    }

    override fun getPlayerProvider(): FcPlayerProvider {
        return playerProvider.get()
    }
}