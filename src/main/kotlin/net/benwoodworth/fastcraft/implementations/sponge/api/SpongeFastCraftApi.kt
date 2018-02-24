package net.benwoodworth.fastcraft.implementations.sponge.api

import com.google.inject.AbstractModule
import net.benwoodworth.fastcraft.dependencies.api.FastCraftApi
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.implementations.sponge.api.gui.SpongeGuiFactory
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeFcItemBuilder
import net.benwoodworth.fastcraft.implementations.sponge.api.player.SpongeFcPlayerProvider
import net.benwoodworth.fastcraft.implementations.sponge.api.text.SpongeFcTextBuilder
import javax.inject.Inject
import javax.inject.Provider

class SpongeFastCraftApi @Inject constructor(
        private val textBuilder: Provider<SpongeFcTextBuilder>,
        private val guiFactory: Provider<SpongeGuiFactory>,
        private val itemBuilder: Provider<SpongeFcItemBuilder>,
        private val playerProvider: Provider<SpongeFcPlayerProvider>
) : AbstractModule(), FastCraftApi {

    override fun configure() = Unit

    @com.google.inject.Provides
    override fun getTextBuilder(): FcTextBuilder {
        return textBuilder.get()
    }

    @com.google.inject.Provides
    override fun getGuiFactory(): GuiFactory {
        return guiFactory.get()
    }

    @com.google.inject.Provides
    override fun getItemBuilder(): FcItemBuilder {
        return itemBuilder.get()
    }

    @com.google.inject.Provides
    override fun getPlayerProvider(): FcPlayerProvider {
        return playerProvider.get()
    }
}