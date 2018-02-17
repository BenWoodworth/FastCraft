package net.benwoodworth.fastcraft.dependencies.api

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.player.PlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.TextBuilder
import javax.inject.Inject
import javax.inject.Provider

/**
 * A FastCraft API for use by other plugins.
 *
 * Can be used as a dependency injection module
 * for [Guice](https://github.com/google/guice/)
 * and [Dagger](https://google.github.io/dagger/).
 */
@dagger.Module
class FastCraftApi @Inject constructor(
        private val textBuilder: Provider<TextBuilder>,
        private val guiFactory: Provider<GuiFactory>,
        private val itemBuilder: Provider<ItemBuilder>,
        private val playerProvider: Provider<PlayerProvider>
) : com.google.inject.AbstractModule() {

    override fun configure() = Unit

    /**
     * Get an instance of a [TextBuilder].
     */
    @dagger.Provides
    @com.google.inject.Provides
    fun getTextBuilder(): TextBuilder {
        return textBuilder.get()
    }

    /**
     * Get an instance of a [GuiFactory].
     */
    @dagger.Provides
    @com.google.inject.Provides
    fun getGuiFactory(): GuiFactory {
        return guiFactory.get()
    }

    /**
     * Get an instance of a [ItemBuilder].
     */
    @dagger.Provides
    @com.google.inject.Provides
    fun getItemBuilder(): ItemBuilder {
        return itemBuilder.get()
    }

    /**
     * Get an instance of a [PlayerProvider].
     */
    @dagger.Provides
    @com.google.inject.Provides
    fun getPlayerProvider(): PlayerProvider {
        return playerProvider.get()
    }
}
