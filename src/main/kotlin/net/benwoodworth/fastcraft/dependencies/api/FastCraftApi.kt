package net.benwoodworth.fastcraft.dependencies.api

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextBuilder
import javax.inject.Inject
import javax.inject.Provider

/**
 * A FastCraft API for use by other plugins.
 */
// * Can be used as a dependency injection module
// * for [Guice](https://github.com/google/guice/)
// * and [Dagger](https://google.github.io/dagger/).
// */
//@dagger.Module
class FastCraftApi @Inject constructor( // TODO Dagger and javax.inject are relocated
        private val textBuilder: Provider<FcTextBuilder>,
        private val guiFactory: Provider<GuiFactory>,
        private val itemBuilder: Provider<FcItemBuilder>,
        private val playerProvider: Provider<FcPlayerProvider>
) /*: com.google.inject.AbstractModule()*/ {

//    override fun configure() = Unit

    /**
     * Get an instance of a [FcTextBuilder].
     */
//    @dagger.Provides
//    @com.google.inject.Provides
    fun getTextBuilder(): FcTextBuilder {
        return textBuilder.get()
    }

    /**
     * Get an instance of a [GuiFactory].
     */
//    @dagger.Provides
//    @com.google.inject.Provides
    fun getGuiFactory(): GuiFactory {
        return guiFactory.get()
    }

    /**
     * Get an instance of a [FcItemBuilder].
     */
//    @dagger.Provides
//    @com.google.inject.Provides
    fun getItemBuilder(): FcItemBuilder {
        return itemBuilder.get()
    }

    /**
     * Get an instance of a [FcPlayerProvider].
     */
//    @dagger.Provides
//    @com.google.inject.Provides
    fun getPlayerProvider(): FcPlayerProvider {
        return playerProvider.get()
    }
}
