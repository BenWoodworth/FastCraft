package net.benwoodworth.fastcraft.sponge.dependencies.text

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.text.ModuleText
import net.benwoodworth.fastcraft.core.dependencies.text.TextBuilder
import net.benwoodworth.fastcraft.core.dependencies.text.TextColorRegistry

/**
 * Sponge implementation of [ModuleText].
 */
@Module
class SpongeModuleText : ModuleText {

    @Provides
    override fun textBuilder(): TextBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Provides
    override fun textColorRegistry(): TextColorRegistry {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
